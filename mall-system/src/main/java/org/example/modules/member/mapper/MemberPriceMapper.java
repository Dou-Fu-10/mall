package org.example.modules.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.member.entity.MemberPriceEntity;

/**
 * Created by Dou-Fu-10 2023-07-15 11:35:10
 *
 * @author Dou-Fu-10
 * @date 2023-07-15 11:35:10
 * @Description 商品会员价格表(MemberPrice)表数据库访问层
 */
@Mapper
public interface MemberPriceMapper extends BaseMapper<MemberPriceEntity> {

}

