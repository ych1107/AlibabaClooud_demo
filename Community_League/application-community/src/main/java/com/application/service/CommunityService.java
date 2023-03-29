package com.application.service;

import com.application.pojo.Community;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dftdla.pojo.QueryPage;
import com.dftdla.result.ResponseResult;

import java.util.List;

/**
 * @author 14501
 */
public interface CommunityService extends IService<Community> {

    /**
     * 社团注册
     * @param community
     * @return
     */
    ResponseResult register(Community community);

    /**
     * 解散社团列表
     * @param communityIds
     * @return
     */
    ResponseResult delete(List<Long> communityIds);


    /**
     * 获取社团列表
     * condition : 社团名称、社团描述模糊搜索
     * 分页查询
     * @param userId
     * @return
     */
    ResponseResult listCommunity(QueryPage userId);

    /**
     * 获取当前登录用户有关的社团
     * @param id
     * @return
     */
    ResponseResult getMyTeam(Long id);

    /**
     * 更新社团描述信息
     * @param id
     * @param description
     * @return
     */
    ResponseResult updateDesc(Long id, String description);

}
