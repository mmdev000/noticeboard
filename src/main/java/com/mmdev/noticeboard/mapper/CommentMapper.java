package com.mmdev.noticeboard.mapper;

import com.mmdev.noticeboard.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from COMMENT_LIST order by id desc")
    List<Comment> getList();

    @Insert("insert into COMMENT_LIST (bid, comment, reguser, regdate, chgdate) VALUES (#{data.bid}, #{data.comment}, #{data.reguser}, now(), now())")
    void insert(@Param("data") Comment data);

    @Update("update COMMENT_LIST set comment = #{data.comment}, chgdate = now() where id = #{data.id} and reguser = #{data.reguser}")
    void update(@Param("data") Comment data);

    @Delete("delete from COMMENT_LIST where id = #{id}")
    void delete(@Param("id")String id);

}
