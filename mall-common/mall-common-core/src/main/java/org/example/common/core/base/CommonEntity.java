package org.example.common.core.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author PanShiFu
 * @date 2023-07-07
 * @Description 抽象实体类 ：带有公共字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class CommonEntity<T extends Model<?>> extends Model<T> implements Serializable {

    /**
     * 创建人
     * 创建
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 更新人
     * 创建、更新
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    /**
     * 创建时间
     * 创建
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     * 创建、更新
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
