package org.example.security.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Dou-Fu-10 2023-07-08
 *
 * @author Dou-Fu-10
 * @date 2023-07-08
 * @Description 角色信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {

    private String authority;
}
