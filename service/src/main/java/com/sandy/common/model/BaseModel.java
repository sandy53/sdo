package com.sandy.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 基础类封装
 * @author zhangyg
 *
 */
@JsonIgnoreProperties(value = { "optimistic" }) //不序列化的字段
public class BaseModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7858050170706503711L;

}
