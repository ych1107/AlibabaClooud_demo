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
@ApiModel(value = "用户社团表",description = "将用户与其加入的社团绑定")
@TableName("user_community")
public class UserCommunity {
    /**
     * 主键
     */
    @ApiModelProperty("用户社团绑定id")
    @TableId
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;
    /**
     * 社团id
     */
    @ApiModelProperty("社团id")
    private Long communityId;
}
