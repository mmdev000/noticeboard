package com.mmdev.noticeboard.controller;

import com.mmdev.noticeboard.mapper.BoardMapper;
import com.mmdev.noticeboard.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    BoardMapper boardMapper;

    private static final String UPLOAD_PATH_LOCAL = "/Users/kmj/upload_local/";

    @RequestMapping("/getList")
    public List<Board> getList() {
        List<Board> getList = boardMapper.getList();
        return getList;
    }

    @RequestMapping("/get/{id}")
    public Board get(@PathVariable(value = "id") String id) {

        Board data = boardMapper.get(id);

        if (data.getImgpath() != null) {
            List<String> fileNames = new ArrayList<>();
            for (File file : FileUtils.listFiles(new File(data.getImgpath()), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)) {
                log.debug("get images : {}", file.getName());

                fileNames.add(file.getName());

            }
            data.setFileNames(fileNames);
        }

        return data;
    }

    @RequestMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@ModelAttribute Board data) {

        if (data.getFiles() != null) {
            data.setImgpath(UPLOAD_PATH_LOCAL + UUID.randomUUID().toString());
            try {
                for (MultipartFile file : data.getFiles()) {
                    String originalfileName = file.getOriginalFilename();
                    File dest = new File(data.getImgpath());
                    dest.mkdir();
                    dest = new File(data.getImgpath() + "/" + originalfileName);

                    file.transferTo(dest);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        boardMapper.insert(data);
    }

    @RequestMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@ModelAttribute Board data) {

        if (data.getFiles() != null) {

            if (data.getImgpath() == null) {
                data.setImgpath(UPLOAD_PATH_LOCAL + UUID.randomUUID().toString());
            }

            try {
                for (MultipartFile file : data.getFiles()) {
                    String originalfileName = file.getOriginalFilename();
                    File dest = new File(data.getImgpath());
                    dest.mkdir();
                    dest = new File(data.getImgpath() + "/" + originalfileName);

                    file.transferTo(dest);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        boardMapper.update(data);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        boardMapper.delete(id);
    }


}
