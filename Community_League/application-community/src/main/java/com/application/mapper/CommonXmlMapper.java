package com.application.mapper;

import com.application.pojo.Community;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author 14501
 * 用于自定义sql语句执行
 */
@Mapper
public interface CommonXmlMapper {

    /**
     * 获取与我有关的社团列表
     * @param id
     * @return
     */
    @Select("select * from community where id in (select community_id from user_community where user_id=#{id})")
    List<Community> myTeams(Long id);

    /**
     * 获取用户是否是社团的管理员
     * @param user_id
     * @param community_id
     * @return
     */
    @Select("select count(*) from user_administrators where user_id = #{user_id} and community_id = #{community_id}")
    Integer isTeamAdmin(Long user_id,Long community_id);

}
