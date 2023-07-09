package org.example.modules.system.entity.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-09 19:57:25
 *
 * @author PanShiFu
 * @date 2023-07-09 19:57:25
 * @Description 用户角色关联(UserRole)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;


}

