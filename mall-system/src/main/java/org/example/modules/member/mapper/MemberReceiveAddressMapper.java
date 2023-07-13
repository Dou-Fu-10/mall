package org.example.modules.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.modules.member.entity.MemberReceiveAddressEntity;

/**
 * Created by Dou-Fu-10 2023-07-13 15:34:49
 *
 * @author Dou-Fu-10
 * @date 2023-07-13 15:34:49
 * @Description 会员收货地址表(MemberReceiveAddress)表数据库访问层
 */
@Mapper
public interface MemberReceiveAddressMapper extends BaseMapper<MemberReceiveAddressEntity> {

}

