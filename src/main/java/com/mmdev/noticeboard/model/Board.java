package com.mmdev.noticeboard.model;

import lombok.Data;

@Data
public class Board {
    private int id;
    private String title;
    private String contents;
    private String regdate;
    private String chgdate;
}
