package org.example.modules.member.entity.dto;

import java.util.Date;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Created by Dou-Fu-10 2023-08-05 13:27:09
 *
 * @author Dou-Fu-10
 * @date 2023-08-05 13:27:09
 * @Description 会员阅读历史记录(MemberReadHistory)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberReadHistoryDto {
    /**
     * ID
     */
    @JsonIgnore
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
    @Schema(name = "productId", description = "商品id")
    private Long productId;

    /**
     * 创建时间
     */
    @JsonIgnore
    @Schema(name = "createTime", description = "创建时间")
    private Date createTime;


}

