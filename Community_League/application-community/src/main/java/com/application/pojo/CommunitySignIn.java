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
@ApiModel(value = "社团签到表",description = "将社团与签到任务链接起来，方便进行更改操作")
@TableName("community_sign_in")
public class CommunitySignIn {
    /**
     * 主键
     */
    @ApiModelProperty("社团签到id")
    @TableId
    private Long id;
    /**
     * 社团id
     */
    @ApiModelProperty("社团id")
    private Long communityId;
    /**
     * 签到起始时间
     */
    @ApiModelProperty("签到起始时间")
    private LocalDateTime startTime;
    /**
     * 签到终止时间
     */
    @ApiModelProperty("签到终止时间")
    private LocalDateTime stopTime;
    /**
     * 签到位置
     */
    @ApiModelProperty("签到位置")
    private String signInSeat;
    /**
     * 签到最长距离
     */
    @ApiModelProperty("签到最长距离")
    private Long maxLength;
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
