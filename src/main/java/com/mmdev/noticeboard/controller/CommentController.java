package com.mmdev.noticeboard.controller;

import com.mmdev.noticeboard.mapper.CommentMapper;
import com.mmdev.noticeboard.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @RequestMapping("/getList/{bid}")
    public List<Comment> getList(@PathVariable String bid) {
        List<Comment> data = commentMapper.getList(bid);
        List<Comment> pidList = new ArrayList<>();

        for (Comment item : data) {
            if (item.getPid() != null) {
                pidList.add(item);
            }
        }

        for (int i = 0; i < data.size(); i++) {
            List<Comment> replyList = new ArrayList<>();
            String id = data.get(i).getId();

            for (int j = 0; j < pidList.size(); j++) {
                if (id.equals(pidList.get(j).getPid())) {
                    replyList.add(pidList.get(j));
                }
            }
            data.get(i).setReplyList(replyList);
        }

        data.removeAll(pidList);

        return data;
    }

    @RequestMapping("/insert")
    public void insert(@RequestBody Comment data) {
        log.debug("insert comment : {}", data);
        commentMapper.insert(data);
    }

    @RequestMapping("/update")
    public void update(@RequestBody Comment data) {
        commentMapper.update(data);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable(value = "id") String id) {
        commentMapper.delete(id);
    }

}
