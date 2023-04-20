package com.application.mapper;

import com.dftdlaSecurityJwt.mapper.UserAuthenticationMapper;
import org.apache.ibatis.annotations.Mapper;

/** security
 *  用户权限信息查询
 *  继承League-core封装SpringSecurity+Jwt的User权限查询接口
 *  依靠用户id获取权限list
 * @author 14501
 */
@Mapper
public interface UserAuthenticeMapper extends UserAuthenticationMapper {
}
