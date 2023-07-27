package org.example.modules.portal.member.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:17
 * @Description 会员等级表(MemberLevel)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLevelDto {
    /**
     * ID
     */
    private Long id;

    /**
     * 会员等级名字
     */
    private String name;
    /**
     * 是否为默认等级：0->是；1->不是
     */
    private Integer defaultStatus;
    /**
     * 免运费标准（多少钱免运费）
     */
    private Double freeFreightPoint;
    /**
     * 是否有免邮特权
     */
    private Integer priviledgeFreeFreight;
    /**
     * 是否有会员价格特权
     */
    private Integer priviledgeMemberPrice;
    /**
     * 注意
     */
    private String note;


}

