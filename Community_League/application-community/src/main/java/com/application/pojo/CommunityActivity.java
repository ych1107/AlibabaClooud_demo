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
@ApiModel(value = "社团活动表",description = "将社团与活动链接起来，方便进行更改操作")
@TableName("community_activity")
public class CommunityActivity {
    /**
     * 主键
     */
    @ApiModelProperty("社团活动id")
    @TableId
    private Long id;
    /**
     * 社团id
     */
    @ApiModelProperty("社团id")
    private Long communityId;
    /**
     * 活动标题
     */
    @ApiModelProperty("活动标题")
    private String title;
    /**
     * 活动描述
     */
    @ApiModelProperty("活动描述")
    private String description;
    /**
     * 报名起始时间
     */
    @ApiModelProperty("报名起始时间")
    private LocalDateTime startTime;
    /**
     * 报名终止时间
     */
    @ApiModelProperty("报名终止时间")
    private LocalDateTime stopTime;
    /**
     * 活动起始时间
     */
    @ApiModelProperty("活动起始时间")
    private LocalDateTime activityStartTime;
    /**
     * 活动终止时间
     */
    @ApiModelProperty("活动终止时间")
    private LocalDateTime activityStopTime;
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
}
