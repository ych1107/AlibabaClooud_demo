package com.application.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 14501
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user_facial_information")
public class UserFacialInformation {
    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 人脸信息
     */
    private String userFace;
}
