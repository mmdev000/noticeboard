package com.mmdev.noticeboard.controller;

import com.mmdev.noticeboard.mapper.BoardMapper;
import com.mmdev.noticeboard.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class BoardController {

    @Autowired
    BoardMapper boardMapper;

    @RequestMapping("/api/getList")
    public List<Board> getList() {

        return boardMapper.getList();
    }
}
