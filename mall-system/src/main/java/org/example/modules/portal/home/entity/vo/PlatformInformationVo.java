package org.example.modules.portal.home.entity.vo;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Created by Dou-Fu-10 2023-07-31 22:28:57
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 22:28:57
 * @Description 平台信息(PlatformInformation)表实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatformInformationVo {
    /**
     * id
     */
    @Schema(name = "id", description = "id")
    private Long id;

    /**
     * 关于我们
     */
    @Schema(name = "aboutUs", description = "关于我们")
    private String aboutUs;
    /**
     * 联系我们
     */
    @Schema(name = "contact", description = "联系我们")
    private String contact;
    /**
     * 平台协议
     */
    @Schema(name = "userAgreement", description = "平台协议")
    private String userAgreement;
    /**
     * 启动图3张，以逗号分割
     */
    @Schema(name = "startupDiagram", description = "启动图3张，以逗号分割")
    private String startupDiagram;


}

