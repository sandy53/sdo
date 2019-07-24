package com.sandy.doc.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sandy.common.util.BASE64ImageUtil;
import com.sandy.doc.enums.UploadType;
import com.sandy.doc.model.Files;
import com.sandy.doc.service.FileService;

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

    private static final String BASE_DIR = System.getProperty("user.home") + "/sdo/images";

    @Resource
    private FileService         fileService;

    private Files genernateFile(MultipartFile imageFile, String uploadType) {
        Files files = new Files();
        files.setCode(getFileCode());
        files.setName(imageFile.getOriginalFilename());
        files.setCreateTime(System.currentTimeMillis());

        StringBuilder path = new StringBuilder();
        path.append(File.separator);
        path.append(getUploadType(uploadType));
        path.append(File.separator);
        path.append(getFolder());
        //TODO
        //   files.setUcode(ucode);
        files.setPath(path.toString());

        //保存到数据库
        fileService.doSave(files);
        return files;
    }

    private String getFileCode() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String getFolder() {
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyyMMdd");
        return LocalDate.now().format(formatter);
    }

    private UploadType getUploadType(String uploadType) {
        if (StringUtils.isEmpty(uploadType)) {
            return UploadType.common;
        }
        UploadType type = UploadType.valueOf(uploadType);
        return type == null ? type : UploadType.common;
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
            names.add(save(multipartFile, uploadType));
        }
        Map<String, Object> map = new HashMap<>();
        map.put("data", names);
        map.put("errno", 0);
        return map;

    }

    private String save(MultipartFile imageFile, String uploadType) {
        Files sdoFile = genernateFile(imageFile, uploadType);
        File file = new File(BASE_DIR + sdoFile.getPath());
        if (!file.exists()) {
            file.mkdirs();
        }
        String fullName = file.getPath() + File.separator + sdoFile.getFileName();
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
        return "/images/" + sdoFile.getPath() + File.separator + sdoFile.getFileName();

    }

}
