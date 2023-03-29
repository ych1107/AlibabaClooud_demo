package com.application.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 14501
 */
@Data
@NoArgsConstructor
@TableName("community_sign_in")
public class CommunitySignIn {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 社团id
     */
    private Long communityId;
    /**
     * 签到起始时间
     */
    private LocalDateTime startTime;
    /**
     * 签到终止时间
     */
    private LocalDateTime stopTime;
    /**
     * 签到位置
     */
    private String signInSeat;
    /**
     * 签到最长距离
     */
    private Long maxLength;
    /**
     * 创建人的用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新人的用户id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
