package com.sandy.common.model;

/**
 *   空模型   用于请求对象不存在时在redis中占位
 * 
 * @author sandy
 * @version $Id: EmptyMode.java, v 0.1 2019年5月21日 下午8:29:15 sandy Exp $
 */
public class EmptyModel extends BaseModel{

    /**  */
    private static final long serialVersionUID = 3048427397809196835L;

    /**
     * 饥饿单例
     */
    private static final EmptyModel EMPTY_MODEL      = new EmptyModel();

    private EmptyModel() {

    }

    /**
     * 获取空模型对象
     * 
     * @return
     */
    public static EmptyModel empty() {
        return EMPTY_MODEL;
    }
}
