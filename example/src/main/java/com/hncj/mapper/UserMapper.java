package com.hncj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hncj.pojo.MyUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 14501
 */
@Mapper
public interface UserMapper extends BaseMapper<MyUser> {
}
