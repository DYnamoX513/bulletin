package com.learn.bulletin.entity;

import lombok.Data;

@Data
public class Role {
    private Long role_id;
    private String code;
    private String name;
    private String description;
}
