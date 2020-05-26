package com.mmdev.noticeboard.mapper;

import com.mmdev.noticeboard.model.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("select * from BOARD_LIST")
    List<Board> getList();

    @Select("select * from BOARD_LIST where id = #{id}")
    Board get(@Param("id")String id);

    @Insert("insert into BOARD_LIST (title, contents, regdate, chgdate) VALUES (#{data.title}, #{data.contents}, now(), now())")
    void insert(@Param("data")Board data);

    @Update("update BOARD_LIST set title = #{data.title}, contents = #{data.contents}, chgdate = now() where id = #{data.id}")
    void update(@Param("data")Board data);

    @Delete("delete from BOARD_LIST where id = #{id}")
    void delete(@Param("id")String id);
}
