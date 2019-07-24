package com.sandy.doc.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sandy.doc.model.Files;
import com.sandy.doc.service.FileService;
import com.sandy.record.enums.RecordEnum;
import com.sandy.record.model.RecordSave;
import com.sandy.record.service.RecordService;

/**
 *   文档业务类
 * 
 * @author sandy
 * @version $Id: FileServiceImpl.java, v 0.1 2019年7月23日 下午7:19:50 sandy Exp $
 */
@Service
public class FileServiceImpl implements FileService {


    @Resource
    private RecordService recordService;

    @Override
    public void doSave(Files file) {
        RecordSave recordSave = new RecordSave(RecordEnum.Files.name(), Arrays.asList(file));
        recordService.doSave(recordSave);
    }


}
