package org.example.security.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.common.core.entity.MemberEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


/**
 * Created by Dou-Fu-10 2023-07-07 09:58:02
 *
 * @author Dou-Fu-10
 * @date 2023-07-08
 * @Description Jwt用户
 */
@Getter
@AllArgsConstructor
public class JwtMember implements UserDetails {

    private final MemberEntity user;
    /**
     * 默认的GrantedAuthority 无法被反序列话所以使用自定义的 Authority
     */

    private final List<Authority> authorities;

    @Override
    @JSONField(serialize = false)
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    @JSONField(serialize = false)
    public String getUsername() {
        return user.getPhone();
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
