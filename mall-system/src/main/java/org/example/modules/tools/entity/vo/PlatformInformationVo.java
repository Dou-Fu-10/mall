package org.example.modules.tools.entity.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Created by Dou-Fu-10 2023-08-10 22:21:34
 *
 * @author Dou-Fu-10
 * @date 2023-08-10 22:21:34
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
    private Set<String> startupDiagram;

}

