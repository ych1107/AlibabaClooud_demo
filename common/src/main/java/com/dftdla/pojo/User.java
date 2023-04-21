package com.dftdla.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 用户表(User)实体类
 *
 * @author 14501
 */
@Data
@NoArgsConstructor
@ApiModel(value = "用户实体类",description = "存储用户信息")
@TableName("sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    /**
     * 主键
     */
    @ApiModelProperty("用户id")
    @TableId
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;
    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickName;
    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    @ApiModelProperty("账号状态（0正常 1停用）")
    private String status;
    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;
    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String phonenumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    @ApiModelProperty("用户性别（0男，1女，2未知）")
    private String sex;
    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private String avatar;
    /**
     * 用户类型（0管理员，1普通用户）
     */
    @ApiModelProperty("用户类型（0管理员，1普通用户）")
    private String userType;
    /**
     * 创建人的用户id
     */
    @ApiModelProperty("创建人的用户id")
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新人的用户id
     */
    @ApiModelProperty("更新人的用户id")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;
}
