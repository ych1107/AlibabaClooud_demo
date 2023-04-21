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
@ApiModel(value = "用户签到表",description = "将用户与其参与的签到绑定")
@TableName("user_sign_in")
public class UserSignIn {
    /**
     * 主键
     */
    @ApiModelProperty("用户签到绑定id")
    @TableId
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;
    /**
     * 签到id
     */
    @ApiModelProperty("签到id")
    private Long signInId;
    /**
     * 用户签到位置
     */
    @ApiModelProperty("用户签到位置")
    private String signInSeat;
}
