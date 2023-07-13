package org.example.modules.member.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-13 15:35:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:35:17
 * @Description 商品会员价格表(MemberPrice)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberPriceDto {
    /**
     * ID
     */
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
    private Double memberPrice;
    /**
     * 会员等级名称
     */
    private String memberLevelName;


}

