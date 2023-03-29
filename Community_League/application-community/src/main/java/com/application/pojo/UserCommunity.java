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
@TableName("user_community")
public class UserCommunity {
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
     * 社团id
     */
    private Long communityId;
}
