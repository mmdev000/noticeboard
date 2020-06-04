package com.mmdev.noticeboard.mapper;

import com.mmdev.noticeboard.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select(
        " select  "
        +" id, board_id as bid, parent_id as pid, comment, reguser, regdate, chgdate "
        +" from COMMENT_LIST where board_id = #{bid} order by id desc"
    )
    List<Comment> getList(@Param("bid") String bid);

    @Insert("insert into COMMENT_LIST (board_id, comment, parent_id, reguser, regdate, chgdate) VALUES (#{data.bid}, #{data.comment}, #{data.pid}, #{data.reguser}, now(), now())")
    void insert(@Param("data") Comment data);

    @Update("update COMMENT_LIST set comment = #{data.comment}, parent_id = #{data.pid}, chgdate = now() where id = #{data.id} and reguser = #{data.reguser}")
    void update(@Param("data") Comment data);

    @Delete("delete from COMMENT_LIST where id = #{id}")
    void delete(@Param("id")String id);

}
 