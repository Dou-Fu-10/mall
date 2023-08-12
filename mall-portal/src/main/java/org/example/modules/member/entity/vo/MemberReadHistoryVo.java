package org.example.modules.member.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.modules.product.entity.vo.ProductVo;

import java.util.Date;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:10
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:10
 * @Description 会员阅读历史记录(MemberReadHistory)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadHistoryVo {
    /**
     * ID
     */
    @Schema(name = "id", description = "ID")
    private Long id;
    /**
     * 会员id
     */
    @JsonIgnore
    @Schema(name = "memberId", description = "会员id")
    private Long memberId;
    /**
     * 商品id
     */
    @JsonIgnore
    @Schema(name = "productId", description = "商品id")
    private Long productId;

    private ProductVo product;

    /**
     * 创建时间
     */
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;


}

