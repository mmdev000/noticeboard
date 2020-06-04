package com.mmdev.noticeboard.model;

import lombok.Data;

import java.util.List;

@Data
public class Comment {
    private String id;
    private String bid;
    private String pid;
    private String comment;
    private String reguser;
    private String regdate;
    private String chgdate;

    private List<Comment> replyList;

}
