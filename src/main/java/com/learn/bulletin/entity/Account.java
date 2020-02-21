package com.learn.bulletin.entity;

import com.learn.bulletin.entity.enums.AccountType;
import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private Long account_id;
    private User user;
    private AccountType account_type;
    private String openid;
    private String access_token;
    private String refresh_token;
    private Date expire_time;
    private Date create_time;
    private Date last_login_time;
}
