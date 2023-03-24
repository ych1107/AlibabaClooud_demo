package com.hncj.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.dftdla.pojo.User;

/**
 * 初期时common未导入MybatisPlus坐标
 * 在此处继承User并设置表名
 *
 * 后续使用时无需此操作，直接使用common中的user即可
 */
@TableName("sys_user")
public class MyUser extends User {
}
