package com.dftdlaSecurityJwt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface UserAuthenticationMapper {

    @Select("SELECT DISTINCT m.`perms` FROM sys_user_role ur LEFT JOIN `sys_role` r ON ur.`role_id` = r.`id` LEFT JOIN `sys_role_menu` rm ON ur.`role_id` = rm.`role_id` LEFT JOIN `sys_menu` m ON m.`id` = rm.`menu_id` WHERE user_id = #{id} AND r.`status` = 0 AND m.`status` = 0")
    List<String> getAuthenticationNames(@Param("id")Long id);

}
