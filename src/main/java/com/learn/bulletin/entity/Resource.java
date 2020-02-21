package com.learn.bulletin.entity;

import com.learn.bulletin.entity.enums.Operation;
import com.learn.bulletin.entity.enums.ResourceType;
import lombok.Data;

@Data
public class Resource {
    private Long resource_id;
    private String name;
    private ResourceType domain;
    private Operation operation;
    private String code;
}
