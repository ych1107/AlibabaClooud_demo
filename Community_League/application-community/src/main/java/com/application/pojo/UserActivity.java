package com.application.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 14501
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "用户活动表",description = "将用户与其参加的活动绑定")
@TableName("user_activity")
public class UserActivity {
    /**
     * 主键
     */
    @ApiModelProperty("用户活动绑定id")
    @TableId
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;
    /**
     * 活动id
     */
    @ApiModelProperty("活动id")
    private Long activityId;
}
