package org.example.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
/**
 * Created by PanShiFu 2023-07-08
 *
 * @author PanShiFu
 * @date 2023-07-08
 * @Description 角色信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {

    private String authority;
}