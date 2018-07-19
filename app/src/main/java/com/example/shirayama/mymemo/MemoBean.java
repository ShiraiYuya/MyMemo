package com.example.shirayama.mymemo;

/**
 * Created by shirayama on 2018/04/18.
 */

public class MemoBean {
    private Integer id = -1;
    private String title = "";
    private String content = "";

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}

