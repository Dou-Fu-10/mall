package org.example.config;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.modules.system.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Created by PanShiFu 2023-07-07 09:58:02
 *
 * @author PanShiFu
 * @date 2023-07-08
 * @Description Jwt用户
 */
@Getter
@AllArgsConstructor
public class JwtUser implements UserDetails {

    private final UserEntity user;
    /**
     * 默认的GrantedAuthority 无法被反序列话所以使用自定义的 Authority
     */
    private final List<Authority> authorities;

    public Set<String> getRoles() {
        // TODO 优化语句
        return authorities.stream().map(Authority::getAuthority).collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    @JSONField(serialize = false)
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JSONField(serialize = false)
    public String getUsername() {
        return user.getUsername();
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JSONField(serialize = false)
    public boolean isEnabled() {
        return user.getEnabled();
    }
}
