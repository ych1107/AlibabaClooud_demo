package com.application.controller;

import com.application.pojo.Community;
import com.application.service.CommunityService;
import com.dftdla.pojo.QueryPage;
import com.dftdla.result.ResponseResult;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 14501
 */
@Slf4j
@Api(tags = "社团操作接口")
@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    /**
     * 创建社团
     * @param community
     * @return
     */
    @ApiOperation(value = "创建社团接口",notes = "创建一个以当前登录用户为指导员的社团")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "name",value = "测试社团",required = true),
            @ApiImplicitParam(name = "description",value = "测试社团的描述",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "创建社团成功！"),
            @ApiResponse(code = 400, message = "社团创建失败！请更换社团名称")
    })
    @PostMapping("/register")
    public ResponseResult register(@RequestBody Community community){
        return communityService.register(community);
    }

    /**
     * 更新社团描述信息
     * @param community
     * @return
     */
    @ApiOperation(value = "更新社团信息",notes = "更新指定社团的社团描述信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "id", value = "测试id"),
            @ApiImplicitParam(name = "name",value = "测试社团",required = true),
            @ApiImplicitParam(name = "description",value = "测试社团的描述",required = true),
            @ApiImplicitParam(name = "mentor",value = "测试社团的指导员"),
            @ApiImplicitParam(name = "createBy",value = "测试社团的创建人id"),
            @ApiImplicitParam(name = "createTime",value = "测试社团的创建时间"),
            @ApiImplicitParam(name = "updateBy",value = "测试社团的更新人id"),
            @ApiImplicitParam(name = "updateTime",value = "测试社团的更新时间")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "社团描述修改成功！"),
            @ApiResponse(code = 400, message = "无权修改！")
    })
    @PutMapping("/updateDesc")
    public ResponseResult update(@RequestBody Community community) {
        return communityService.updateDesc(community.getId(), community.getDescription());
    }

    /**
     * 获取社团列表
     * 若有字符串传入，则返回相关的社团列表
     * @param queryPage
     * @return
     */
    @ApiOperation(value = "获取社团列表接口",notes = "根据用户传入的检索条件搜索数据并分页返回")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "pageNumber", value = "页码", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页大小", required = true),
            @ApiImplicitParam(name = "condition")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "检索分页查询成功！")
    })
    @GetMapping("/list")
    public ResponseResult list(@RequestBody QueryPage queryPage) {
        return communityService.listCommunity(queryPage);
    }

    /**
     * 解散社团
     * @param communityIds
     * @return
     */
    @ApiOperation(value = "解散社团",notes = "解散传入的一系列社团")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "communityIds", value = "社团id", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "社团解散成功！"),
            @ApiResponse(code = 400, message = "社团解散失败！请确定你有权解散这些社团且他们都存在")
    })
    @DeleteMapping("/delete")
    public ResponseResult delete(@RequestBody List<Long> communityIds){
        return communityService.delete(communityIds);
    }

    /**
     * 获取双创社团数据
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
    @ApiOperation(value = "获取用户相关社团接口",notes = "返回和当前用户有关的全部社团信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", required = true),
            @ApiImplicitParam(name = "id",value = "用户id",required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "读取成功")
    })
    @GetMapping("/myTeam")
    public ResponseResult myTeam(@RequestBody Long id){
        return communityService.getMyTeam(id);
    }

}
