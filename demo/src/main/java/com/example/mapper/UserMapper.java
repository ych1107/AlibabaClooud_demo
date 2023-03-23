package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dftdla.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/** security
 *  sys_user表查询
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
