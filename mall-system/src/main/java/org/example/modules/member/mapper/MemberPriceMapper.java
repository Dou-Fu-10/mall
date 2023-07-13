package org.example.modules.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.member.entity.MemberPriceEntity;

/**
 * Created by PanShiFu 2023-07-13 15:35:17
 *
 * @author PanShiFu
 * @date 2023-07-13 15:35:17
 * @Description 商品会员价格表(MemberPrice)表数据库访问层
 */
@Mapper
public interface MemberPriceMapper extends BaseMapper<MemberPriceEntity> {

}

