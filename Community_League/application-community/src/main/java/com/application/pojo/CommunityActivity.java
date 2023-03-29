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
@TableName("community_activity")
public class CommunityActivity {
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
     * 活动标题
     */
    private String title;
    /**
     * 活动描述
     */
    private String description;
    /**
     * 报名起始时间
     */
    private LocalDateTime startTime;
    /**
     * 报名终止时间
     */
    private LocalDateTime stopTime;
    /**
     * 活动起始时间
     */
    private LocalDateTime activityStartTime;
    /**
     * 活动终止时间
     */
    private LocalDateTime activityStopTime;
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
