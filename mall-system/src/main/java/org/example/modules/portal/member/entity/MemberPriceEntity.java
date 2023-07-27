package org.example.modules.portal.member.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-07-15 11:35:10
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:10
 * @Description 商品会员价格表(MemberPrice)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pms_member_price")
@Schema(name = "pms_member_price", description = "商品会员价格表(MemberPrice)表实体类")
public class MemberPriceEntity extends CommonEntity<MemberPriceEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 商品价格
     */
    private Long productId;
    /**
     * 会员等级id
     */
    private Long memberLevelId;

    /**
     * 会员价格
     */
    @Schema(name = "memberPrice", description = "会员价格")
    private Double memberPrice;
    /**
     * 会员等级名称
     */
    @Schema(name = "memberLevelName", description = "会员等级名称")
    private String memberLevelName;


}
