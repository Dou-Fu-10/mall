package org.example.modules.system.entity;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

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
@TableName("ums_user_role")
@Schema(name = "ums_user_role", description = "用户角色关联(UserRole)表实体类")
public class UserRoleEntity extends CommonEntity<UserRoleEntity> implements Serializable {
    /**
     * 用户ID
     */
    @TableId
    private Long userId;
    /**
     * 角色ID
     */
    @TableId
    private Long roleId;


}

