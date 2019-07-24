package com.sandy.doc.service;

import com.sandy.doc.model.Files;

/**
 *  文件上传接口
 * 
 * @author sandy
 * @version $Id: FileService.java, v 0.1 2019年7月23日 下午7:20:00 sandy Exp $
 */
public interface FileService {

    /**
     *  保存文档
     * 
     */
    void doSave(Files file);



}
