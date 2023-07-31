package org.example.modules.admin.finance.entity;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.example.common.core.base.CommonEntity;

/**
 * Created by Dou-Fu-10 2023-07-31 15:32:47
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:32:47
 * @Description (MemberDescription)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ums_member_description")
@Schema(name = "ums_member_description", description = "(MemberDescription)表实体类")
public class MemberDescriptionEntity extends CommonEntity<MemberDescriptionEntity> implements Serializable {
    /**
     * Id
     */
    @TableId
    private Long id;

    /**
     * 标题
     */
    @Schema(name = "title", description = "标题")
    private String title;
    /**
     * 详情
     */
    @Schema(name = "description", description = "详情")
    private String description;


}

