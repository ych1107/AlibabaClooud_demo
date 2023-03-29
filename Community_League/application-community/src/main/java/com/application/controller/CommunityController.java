package com.application.controller;

import com.application.pojo.Community;
import com.application.service.CommunityService;
import com.dftdla.pojo.QueryPage;
import com.dftdla.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14501
 */
@RestController
@RequestMapping("/community")
@Slf4j
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    /**
     * 创建社团
     * @param community
     * @return
     */
    @PostMapping("/register")
    public ResponseResult register(@RequestBody Community community){
        return communityService.register(community);
    }

    /**
     * 更新社团描述信息
     * @param community
     * @return
     */
    @PutMapping("/updateDesc")
    public ResponseResult update(@RequestBody Community community) {
        return this.communityService.updateDesc(community.getId(), community.getDescription());
    }

    /**
     * 获取社团列表
     * 若有字符串传入，则返回相关的社团列表
     * @param queryPage
     * @return
     */
    @GetMapping("/list")
    public ResponseResult list(@RequestBody QueryPage queryPage) {
        return communityService.listCommunity(queryPage);
    }

    /**
     * 解散社团
     * @param communityIds
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseResult delete(@RequestBody List<Long> communityIds){
        return communityService.delete(communityIds);
    }

    /**
     * 获取双创社团
     * @return ResponseResult
     */
    @GetMapping("/TheAdminTeam")
    public ResponseResult adminTeam(){
        return new ResponseResult(HttpStatus.OK.value(),communityService.getById(1L));
    }

    /**
     * 获取与用户有关的社团信息
     * @return ResponseResult
     */
    @GetMapping("/myTeam")
    public ResponseResult myTeam(@RequestBody Long id){
        return communityService.getMyTeam(id);
    }

}
