package org.example.modules.member.entity.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.modules.product.entity.vo.ProductVo;

import java.util.List;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员收藏(MemberCollection)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberCollectionVo {
    /**
     * ID
     */
    @JsonIgnore
    @Schema(name = "id", description = "ID")
    private Long id;
    /**
     * 会员id
     */
    @Schema(name = "memberId", description = "会员id")
    private Long memberId;
    /**
     * 商品id
     */
    @JsonIgnore
    @Schema(name = "productId", description = "商品id")
    private Long productId;

    private List<ProductVo> productList;

}

