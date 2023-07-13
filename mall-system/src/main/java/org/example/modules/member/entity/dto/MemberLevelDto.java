package org.example.modules.member.entity.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-13 14:28:44
 *
 * @author PanShiFu
 * @date 2023-07-13 14:28:44
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

