package com.sandy.user.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandy.auth.core.annotation.AuthAnonymous;
import com.sandy.auth.core.enums.AuthKey;
import com.sandy.auth.core.model.AuthUser;
import com.sandy.common.model.ReqResult;
import com.sandy.common.model.ResultCode;
import com.sandy.common.redis.RedisTimeOut;
import com.sandy.common.redis.RedisUtil;
import com.sandy.common.redis.key.CommonKey;
import com.sandy.common.util.CodeUtils;
import com.sandy.common.util.EmailUtil;
import com.sandy.user.model.Token;
import com.sandy.user.model.Users;
import com.sandy.user.service.UserService;

/**
 * 
 *   用户控制器
 * 
 * @author sandy
 * @version $Id: UserController.java, v 0.1 2019年7月24日 下午7:37:55 sandy Exp $
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger     logger    = LoggerFactory.getLogger(UserController.class);
    private static final int  VCODE_LEN = 6;

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil<Object> redisUtil;


    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 获取邮箱验证码
     */

    @AuthAnonymous
    @RequestMapping("/vcode")
    public Object fetchVcode(@RequestParam("email") String email) {
        int vcode = CodeUtils.generateRandomInt(VCODE_LEN);
        logger.error("sendMail: {} -> {}", email, vcode);
        try {
            //邮件发送
            EmailUtil.sendMail(email, "文档中心验证码", "验证码: " + vcode);
        } catch (Exception e) {
            logger.error("fetchVcode E: {}", email, e);
            return new ReqResult<>(ResultCode.USER_EMAIL_SEND_FAIL);
        }
        redisUtil.set(CommonKey.USER_VERIFY_CODE.setSuffex(vcode), vcode, RedisTimeOut.S_120);
        return new ReqResult<>();
    }

    /**
     *  登陆
     * 
     * @param email
     * @return
     */
    @AuthAnonymous
    @RequestMapping("/login/email")
    public Object loginByEmail(@RequestParam("email") String email,
                                @RequestParam("vcode") Integer vcode) {
        Object object = redisUtil.get(CommonKey.USER_VERIFY_CODE.setSuffex(vcode));
        if (object == null || !(object instanceof Integer)) {
            return new ReqResult<>(ResultCode.VCODE_MISMATCH);
        }
        if (!vcode.equals(object)) {
            return new ReqResult<>(ResultCode.VCODE_MISMATCH);
        }

        Users user = userService.doLogin(email);
        AuthUser authUser = new AuthUser();
        authUser.setUserId(user.getUcode());
        authUser.setUserName(user.getEmail());
        redisTemplate.opsForHash().put(AuthKey.AUTH_USER_KEY.getKey(), user.getUcode().toString(),
            authUser);
        Token token = genernateToken(user.getUcode().toString());
        return new ReqResult<>(token);
    }

    /**
     * 生成token 
     * 
     * @param userIdOfToken    通过TokenUtil  生成的带有用户类型标识的  用户编号
     * @return
     */
    private Token genernateToken(String userIdOfToken) {
        Token token = new Token();
        long currentTimeMillis = System.currentTimeMillis();
        String tokenVal = UUID.randomUUID().toString();
        long tokenExpires = currentTimeMillis + RedisTimeOut.S_600 * 1000 * 1000; //1000分钟 毫秒数
        redisUtil.set(CommonKey.AUTH_TOKEN_USER_PREFIX.setSuffex(tokenVal), userIdOfToken,
            RedisTimeOut.S_86400 * 10); //十天
        String refreshToken = UUID.randomUUID().toString();
        long refreshExpires = currentTimeMillis + RedisTimeOut.S_86400 * 10 * 1000; //十天
        redisUtil.set(CommonKey.AUTH_TOKEN_REFRESH_PREFIX.setSuffex(refreshToken), userIdOfToken,
            RedisTimeOut.S_86400 * 30); // 三十天
        token.setAccess_token(tokenVal);
        token.setToken_expires(tokenExpires);
        token.setRefresh_token(refreshToken);
        token.setRefresh_expires(refreshExpires);
        logger.error("***GENERNATE-TOKEN*************: {}, {}, {} ", userIdOfToken, tokenVal,
            token);
        return token;
    }

}
