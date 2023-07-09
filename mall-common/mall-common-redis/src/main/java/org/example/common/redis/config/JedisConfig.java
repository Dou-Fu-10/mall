package org.example.common.redis.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author PanShiFu
 * @date 2023-07-08
 * @Description redis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
//@ConfigurationProperties(prefix = "spring.redis.jedis.jedis-config", ignoreUnknownFields = true)
public class JedisConfig {
    private String master = "master";

    private String password = "redispwd";

    /**
     * 做空闲资源检测时，每次检测资源的个数。
     * 默认值：3
     * 建议：可根据自身应用连接数进行微调，如果设置为 -1，就是对所有连接做空闲监测。
     */
    private int numTestsPerEvictionRun = -1;
    /**
     * 向资源池借用连接时是否做连接有效性检测（ping）。检测到的无效连接将会被移除。
     * 默认值：false
     * 建议：业务量很大时候建议设置为false，减少一次ping的开销。
     */
    private Boolean testOnBorrow = false;
    /**
     * 向资源池归还连接时是否做连接有效性检测（ping）。检测到无效连接将会被移除。
     * 默认值：false
     * 建议：业务量很大时候建议设置为false，减少一次ping的开销。
     */
    private Boolean testOnReturn = false;
    /**
     * 是否在空闲资源监测时通过ping命令监测连接有效性，无效连接将被销毁。
     * 默认值：false
     * 建议：true
     */
    private Boolean testWhileIdle = true;
    /**
     * 当资源池用尽后，调用者是否要等待。只有当值为true时，下面的maxWaitMillis才会生效。
     * 默认值：true
     * 建议：使用默认值。
     */
    private Boolean blockWhenExhausted = true;
    /**
     * 是否开启JMX监控
     * 默认值：true
     * 建议：开启，请注意应用本身也需要开启。
     */
    private Boolean jmxEnabled = true;
    /**
     * 资源池中的最大连接数
     * 默认值：8
     * 建议：参见 https://help.aliyun.com/document_detail/98726.html#section-m2c-5kr-zfb
     */
    private int maxTotal = 8;
    /**
     * 资源池允许的最大空闲连接数
     * 默认值：8
     * 建议：参见 https://help.aliyun.com/document_detail/98726.html#section-m2c-5kr-zfb
     */
    private int maxIdle = 8;
    /**
     * 资源池确保的最少空闲连接数
     * 默认值：0
     * 建议：参见 https://help.aliyun.com/document_detail/98726.html#section-m2c-5kr-zfb
     */
    private int minIdle = 0;

}
