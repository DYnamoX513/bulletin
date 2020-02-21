package com.learn.bulletin.entity.enums;

public enum ResourceType {
    USER("用户系统"),
    NEWS("新闻系统"),
    TAGS("标签系统"),
    COLUMN("栏目系统");

    private String text;

    ResourceType(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
