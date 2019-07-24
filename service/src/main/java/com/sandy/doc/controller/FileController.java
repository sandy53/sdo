package com.sandy.doc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sandy.common.util.BASE64ImageUtil;

/**
 * 图片，文件上传控制器
 * 
 * @author sandy
 * @version $Id: FileController.java, v 0.1 2019年7月23日 上午9:28:48 sandy Exp $
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private Logger              logger   = LoggerFactory.getLogger(BASE64ImageUtil.class);

    private static final String BASE_DIR = System.getProperty("user.home");

    private String save(MultipartFile imageFile) {
        
        File file = new File(BASE_DIR + "/sdo/test/images");
        if(!file.exists()) {
            file.mkdirs();
        }
        String fullName = file.getPath() + File.separator + imageFile.getOriginalFilename();
        file = new File(fullName);
        try (InputStream inputStream = imageFile.getInputStream();
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));) {
            int len = 0;
            byte[] b = new byte[1024 * 10];
            while ((len = inputStream.read(b)) != -1) {
                bos.write(b, 0, len);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return "/images/" + imageFile.getOriginalFilename();

        /*      BufferedImage buffImg = null;
        try  {
        
            buffImg = ImageIO.read(imageFile.getInputStream());
            boolean write = ImageIO.write(buffImg, "jpg", file);
            System.err.println(write);
        } catch (IOException e) {
            logger.error("", e);
        }*/

    }


    /**
     * TODO
     * @author chenlong
     * 上传Base64格式的图片
     * @param fileStr
     * @param uploadType 
     * @return
     */
    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    @ResponseBody
    public Object doUpload(@RequestParam(value = "imgFile", required = true) MultipartFile[] images,
                                   String uploadType) {

        System.err.println(images);
        List<String> names = new ArrayList<>();
        for (MultipartFile multipartFile : images) {
            //
            names.add(save(multipartFile));
        }

        /* Assert.notNull(file);
        String filePath = fileMainDir;
        uploadType = StringUtil.isBlank(uploadType) ? "" : uploadType;
        filePath = filePath + FileUtil.getFilePath(uploadType) + "/";
        RespCode resultCode = ResultCode.SUCCESS;
        Files record = null;
        try {
            long fileSize = file.getSize();
            if (fileSize > PlatConfig.getMaxImageSize()) {
                resultCode = ResultCode.FILE_UPLOAD_TOOLARGE;
                // msg = "图片上传失败,图片大小不能超过2MB";
            } else {
                String fileExt = FileUtil.getFileExt(file.getOriginalFilename());
                record = fileService.uploadFile(PlatConfig.getBucketName(), fileExt, filePath,
                    file.getInputStream());
            }
        } catch (BusinessException e) {
            resultCode = e.getCode();
            logger.error("FileController", e);
        } catch (Exception e) {
            logger.error("FileController", e);
            resultCode = ResultCode.FILE_UPLOAD_FAIL;
        }*/
        //result.setData(record);

        Map<String, Object> map = new HashMap<>();
        
        map.put("data", names);
        map.put("errno", 0);
        return map;

    }
}
