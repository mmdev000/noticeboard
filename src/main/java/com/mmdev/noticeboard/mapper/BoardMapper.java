package com.mmdev.noticeboard.mapper;

import com.mmdev.noticeboard.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("select * from BOARD_LIST")
    List<Board> getList();
}
