package com.mmdev.noticeboard.model;

import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Data
public class Board {
    private String id;
    private String title;
    private String contents;
    private String imgpath;
    @Nullable
    private List<MultipartFile> files;
    private String regdate;
    private String chgdate;

    private List<String> fileNames;
}
