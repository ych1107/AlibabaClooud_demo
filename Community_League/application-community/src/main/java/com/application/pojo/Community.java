package com.application.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 14501
 */
@Data
@NoArgsConstructor
@ApiModel(value = "社团实体类",description = "定义了社团信息")
@TableName("community")
public class Community {

    /**
     * 主键
     */
    @ApiModelProperty("社团id")
    @TableId
    private Long id;
    /**
     * 指导员
     */
    @ApiModelProperty("社团指导员")
    private Long mentor;
    /**
     * 社团名称
     */
    @ApiModelProperty("社团名称")
    private String name;
    /**
     * 社团描述
     */
    @ApiModelProperty("社团描述")
    private String description;
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

    public Community(Long mentor, String name, String description){
        this.mentor = mentor;
        this.name = name;
        this.description = description;
    }

}
