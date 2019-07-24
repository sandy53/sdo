package com.sandy.common.util;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.sandy.common.exception.RuningException;
import com.sandy.common.model.ResultCode;

import sun.misc.BASE64Decoder;

/**
 * 
 * Base64图片处理
 * @author chenlong
 *
 */
@SuppressWarnings("restriction")
public class BASE64ImageUtil {
    private static Logger logger = LoggerFactory.getLogger(BASE64ImageUtil.class);

    /**
     * TODO
     * 将BASE64格式图片转成 MultipartFile
     * @param base64 
     * @return
     * @throws BusinessException 
     */
    public static MultipartFile base64ToMultipart(String base64) {
        if (StringUtils.isBlank(base64))
            return null;

        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RuningException(ResultCode.FILE_UPLOAD_TRANSCODE);
        }
    }

}
