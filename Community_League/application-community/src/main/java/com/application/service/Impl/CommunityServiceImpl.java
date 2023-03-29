package com.application.service.Impl;

import com.application.mapper.CommonXmlMapper;
import com.application.mapper.CommunityMapper;
import com.application.mapper.UserAdministratorsMapper;
import com.application.pojo.Community;
import com.application.pojo.UserAdministrator;
import com.application.service.CommunityService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dftdla.pojo.QueryPage;
import com.dftdla.result.ResponseResult;
import com.dftdla.util.BaseContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 14501
 */
@Service
@Slf4j
public class CommunityServiceImpl extends ServiceImpl<CommunityMapper,Community> implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private CommonXmlMapper commonXmlMapper;

    @Autowired
    private UserAdministratorsMapper userAdministratorsMap;

    /**
     * GlobalTransactional 分布式事务
     * @param community
     * @return
     */
    @GlobalTransactional
    @Override
    public ResponseResult register(Community community) {

        community.setMentor(BaseContext.getId());

        try {

            communityMapper.insert(community);

            userAdministratorsMap.insert(new UserAdministrator(null,community.getMentor(),community.getId()));

            return new ResponseResult(HttpStatus.SC_OK,"社团创建成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"社团创建失败,请更换社团名称");
        }
    }

    @GlobalTransactional
    @Override
    public ResponseResult delete(List<Long> communityIds) {

        List<Community> communities = communityMapper.selectBatchIds(communityIds);

        for (Community community: communities){
            if (community == null) {
                return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"社团不存在！");
            }else if(!community.getMentor().equals(BaseContext.getId())){
                return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"你无权解散:"+community.getName()+"!");
            }
        }

        try {
            communityMapper.deleteBatchIds(communityIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"社团解散失败!");
        }

        return new ResponseResult(HttpStatus.SC_OK,"社团解散成功！");
    }

    @Override
    public ResponseResult listCommunity(QueryPage queryPage) {

        Page<Community> page = new Page<>(queryPage.getPageNumber(), queryPage.getPageSize());

        Object condition = queryPage.getCondition();

        if (condition == null) {
            return new ResponseResult(HttpStatus.SC_OK,communityMapper.selectPage(page,null));
        }

        String s = condition.toString();

        QueryWrapper<Community> communityQueryWrapper = new QueryWrapper<>();

        communityQueryWrapper.lambda()
                .like(Community::getName,s)
                .or()
                .like(Community::getDescription,s);

        return new ResponseResult(HttpStatus.SC_OK,communityMapper.selectPage(page,communityQueryWrapper));
    }

    @Override
    public ResponseResult getMyTeam(Long id) {

        return new ResponseResult(HttpStatus.SC_OK,commonXmlMapper.myTeams(id));

    }

    @Override
    public ResponseResult updateDesc(Long id, String description) {
        if(commonXmlMapper.isTeamAdmin(BaseContext.getId(),id)<=0){
            return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"你无权更新此社团！");
        }
        Community community = new Community();
        community.setId(id);
        community.setDescription(description);
        try {
            communityMapper.updateById(community);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(HttpStatus.SC_BAD_REQUEST,"社团更新失败！");
        }
        return new ResponseResult(HttpStatus.SC_OK,"社团更新成功！");
    }
}
