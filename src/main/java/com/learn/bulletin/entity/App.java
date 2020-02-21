package com.learn.bulletin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class App {
    private Long app_id;
    private String client_id;
    private String name;
    private String img1X;
    private Date create_time;

    private String resource_id;
    private String client_secret;
    private String scope;
    private String authorized_grant_types;
    private String web_server_redirect_uri;
    private String authorities;
    private Integer access_token_validity;
    private Integer refresh_token_validity;
    private String additional_information;
    private String auto_approve;
}
