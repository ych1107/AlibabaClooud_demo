package com.dftdlaSecurityJwt.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.dftdla.pojo.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 14501
 */
@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User user;

    private List<String> permission;

    /**
     * redis为了安全起见，不会序列化它，此时会导致报错
     * 序列化时忽略
     */
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(User user,List<String> permissions) {
        this.user = user;
        this.permission = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        List<GrantedAuthority> list = new ArrayList<>();
//        for (String perm: permission) {
//            list.add(new SimpleGrantedAuthority(perm));
//        }
//        return list;

        if(authorities!=null) {
            return authorities;
        }
        //函数式编程
        authorities=permission.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
