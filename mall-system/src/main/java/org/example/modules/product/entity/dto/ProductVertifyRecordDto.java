package org.example.modules.product.entity.dto;

import java.util.Date;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * Created by PanShiFu 2023-07-14 13:54:18
 *
 * @author PanShiFu
 * @date 2023-07-14 13:54:18
 * @Description 商品审核记录(ProductVertifyRecord)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVertifyRecordDto {
    /**
     * ID
     */
    private Long id;
    /**
     * 产品id
     */
    private Long productId;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 审核人
     */
    private String vertifyMan;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 反馈详情
     */
    private String detail;


}

