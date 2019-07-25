package com.sandy.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sandy.common.Assert;
import com.sandy.common.exception.RuningException;
import com.sandy.common.model.ResultCode;
import com.sandy.common.util.CodeUtils;
import com.sandy.user.dao.UsersMapper;
import com.sandy.user.model.Users;
import com.sandy.user.service.UserService;

/**
 *  用户业务类
 * 
 * @author sandy
 * @version $Id: UserServiceImpl.java, v 0.1 2019年7月24日 下午7:36:36 sandy Exp $
 */
@Service
public class UserServiceImpl implements UserService {
    private static final int UCODE_LEN = 8;
    @Resource
    private UsersMapper usersMapper;


    @Override
    public Users doLogin(String email) {
        Assert.notEmpty(email);
        Users user = usersMapper.selectByEmail(email);
        if(user != null) {
            return user;
        }
        //初始进数据库
        user = new Users();
        user.setCreateTime(System.currentTimeMillis());
        user.setEmail(email);
        user.setUcode(CodeUtils.generateRandomInt(UCODE_LEN));
        
        int insert = usersMapper.insert(user);
        if (insert <= 0) {
            throw new RuningException(ResultCode.RECORD_SAVE_FAIL);
        }
        return user;
    }

}
