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
@TableName("user_sign_in")
public class UserSignIn {
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
     * 签到id
     */
    private Long signInId;
    /**
     * 用户签到位置
     */
    private String signInSeat;
}
