package com.mmdev.noticeboard.controller;

import com.mmdev.noticeboard.mapper.CommentMapper;
import com.mmdev.noticeboard.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @RequestMapping("/getList")
    public List<Comment> getList() {
        return commentMapper.getList();
    }

    @RequestMapping("/insert")
    public void insert(@RequestBody Comment data) {
        log.debug("insert comment : {}", data);
        commentMapper.insert(data);
    }

    @RequestMapping("/update")
    public void update(@ModelAttribute Comment data) {
        commentMapper.update(data);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        commentMapper.delete(id);
    }

}
