package com.mmdev.noticeboard.controller;

import com.mmdev.noticeboard.mapper.BoardMapper;
import com.mmdev.noticeboard.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    BoardMapper boardMapper;

    @RequestMapping("/getList")
    public List<Board> getList() {
        List<Board> getList = boardMapper.getList();
        log.info("log.info");
        log.warn("log.warn");
        log.debug("log.debug");
        log.trace("log.trace");
        log.error("log.error");
        return getList;
    }

    @RequestMapping("/get/{id}")
    public Board get(@PathVariable(value = "id") String id) {
        return boardMapper.get(id);
    }

    @RequestMapping("/insert")
    public void insert(@RequestBody Board data) {
        boardMapper.insert(data);
    }

    @RequestMapping("/update")
    public void update(@RequestBody Board data) {
        boardMapper.update(data);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        boardMapper.delete(id);
    }
}
