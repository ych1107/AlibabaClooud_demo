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
@ApiModel(value = "用户脸部信息表",description = "将用户与其自身的面部识别信息绑定")
@TableName("user_facial_information")
public class UserFacialInformation {
    /**
     * 主键
     */
    @ApiModelProperty("用户面部信息绑定id")
    @TableId
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;
    /**
     * 人脸信息
     */
    @ApiModelProperty("人脸信息")
    private String userFace;
}
