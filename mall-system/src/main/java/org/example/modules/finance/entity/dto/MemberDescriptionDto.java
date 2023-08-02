package org.example.modules.finance.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Dou-Fu-10 2023-07-31 15:40:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:40:17
 * @Description 会员详细说明(MemberDescription)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDescriptionDto {
    /**
     * Id
     */
    private Long id;

    /**
     * 排序
     */
    private Integer sort;
    /**
     * 标题
     */
    private String title;
    /**
     * 详情
     */
    private String description;


}

