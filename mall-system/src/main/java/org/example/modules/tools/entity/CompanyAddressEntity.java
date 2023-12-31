package org.example.modules.tools.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.common.core.base.CommonEntity;
import org.example.common.core.validation.PhoneValid;

import java.io.Serializable;

/**
 * Created by Dou-Fu-10 2023-07-14 14:36:01
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:36:01
 * @Description 公司收发货地址表(CompanyAddress)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("oms_company_address")
@Schema(name = "oms_company_address", description = "公司收发货地址表(CompanyAddress)表实体类")
public class CompanyAddressEntity extends CommonEntity<CompanyAddressEntity> implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 地址名称
     */
    @Schema(name = "addressName", description = "地址名称")
    private String addressName;
    /**
     * 默认发货地址：0->否；1->是
     */
    @Schema(name = "sendStatus", description = "默认发货地址：0->否；1->是")
    private Boolean sendStatus;
    /**
     * 是否默认收货地址：0->否；1->是
     */
    @Schema(name = "receiveStatus", description = "是否默认收货地址：0->否；1->是")
    private Boolean receiveStatus;
    /**
     * 收发货人姓名
     */
    @Schema(name = "name", description = "收发货人姓名")
    private String name;
    /**
     * 收货人电话
     */
    @PhoneValid
    @Schema(name = "phone", description = "收货人电话")
    private String phone;
    /**
     * 省/直辖市
     */
    @Schema(name = "province", description = "省/直辖市")
    private String province;
    /**
     * 市
     */
    @Schema(name = "city", description = "市")
    private String city;
    /**
     * 区
     */
    @Schema(name = "region", description = "区")
    private String region;
    /**
     * 详细地址
     */
    @Schema(name = "detailAddress", description = "详细地址")
    private String detailAddress;


}

