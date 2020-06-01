package com.mmdev.noticeboard.model;

import lombok.Data;

@Data
public class Comment {
    private String id;
    private String bid;
    private String comment;
    private String reguser;
    private String regdate;
    private String chgdate;

}
