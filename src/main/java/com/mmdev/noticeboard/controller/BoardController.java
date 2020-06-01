package com.mmdev.noticeboard.controller;

import com.mmdev.noticeboard.mapper.BoardMapper;
import com.mmdev.noticeboard.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            for (File file : FileUtils.listFiles(new File(UPLOAD_PATH_LOCAL + data.getImgpath().split("/")[0]), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)) {
                log.debug("get images : {}", file.getName());

                fileNames.add(file.getName());

            }
            data.setFileNames(fileNames);
        }

        return data;
    }

    @RequestMapping("/getImg/{id}")
    public void getImg(@PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String URLAfterWebDomain = request.getRequestURI();
        log.debug("getImg {} ", URLAfterWebDomain);
        if(URLAfterWebDomain.startsWith("/api/getImg/") == false) {
            return;
        }

        Board data = boardMapper.get(id);

        if(data.getImgpath() == null || data.getImgpath().equals("")) {
            return;
        }
        log.debug("Fetching image from {}", UPLOAD_PATH_LOCAL + data.getImgpath());
        response.setContentType("image/pjpeg");

        ServletOutputStream outputStream;
        outputStream = response.getOutputStream();
        FileInputStream fin = new FileInputStream(UPLOAD_PATH_LOCAL + data.getImgpath());

        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(outputStream);
        int ch = 0;
        while((ch=bin.read()) != -1) {
            bout.write(ch);
        }

        bin.close();
        fin.close();
        bout.close();
        outputStream.close();

    }

    @RequestMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@ModelAttribute Board data) {

        if (data.getFiles() != null) {
            String dirPath = UPLOAD_PATH_LOCAL + UUID.randomUUID().toString();
            try {
                for (MultipartFile file : data.getFiles()) {
                    String originalfileName = file.getOriginalFilename();
                    File dest = new File(dirPath);
                    dest.mkdir();

                    dest = new File(dirPath + "/" + originalfileName);

                    file.transferTo(dest);

                    data.setImgpath(dirPath.substring(UPLOAD_PATH_LOCAL.length()));
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

            try {
                for (MultipartFile file : data.getFiles()) {
                    String originalfileName = file.getOriginalFilename();

                    String setPath;
                    if (data.getImgpath() == null || data.getImgpath().equals("null")) {
                        setPath = UPLOAD_PATH_LOCAL + UUID.randomUUID().toString();
                    } else {
                        setPath = UPLOAD_PATH_LOCAL + data.getImgpath().split("/")[0];
                    }

                    File dest = new File(setPath);
                    dest.mkdir();
                    dest = new File(setPath + "/" + originalfileName);

                    file.transferTo(dest);

                    data.setImgpath(setPath.substring(UPLOAD_PATH_LOCAL.length()));
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

    @RequestMapping("/fileDownload/{id}/{fileName}")
    public ResponseEntity<Resource> fileDownload(@PathVariable String id, @PathVariable String fileName, HttpServletRequest request, HttpServletResponse response) {

        Board data = boardMapper.get(id);

        File target = new File(UPLOAD_PATH_LOCAL + data.getImgpath() + "/" + fileName);

        HttpHeaders header = new HttpHeaders();

        Resource rs = null;

        if (target.exists()) {
            try {
                rs = new UrlResource(target.toURI());

                header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+rs.getFilename()+"\"");

                header.setCacheControl("no-cache");

            } catch (Exception e) {
                log.error("error {}", e);
            }
        }

        return new ResponseEntity<>(rs, header, HttpStatus.OK);
    }


}
