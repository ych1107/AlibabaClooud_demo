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
@ApiModel(value = "用户承办者表",description = "将用户与其承办的活动绑定")
@TableName("user_undertaker")
public class UserUndertaker {
    /**
     * 主键
     */
    @ApiModelProperty("用户承办绑定id")
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
