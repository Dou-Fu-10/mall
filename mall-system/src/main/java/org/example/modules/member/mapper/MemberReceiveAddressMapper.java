package org.example.modules.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.modules.member.entity.MemberReceiveAddressEntity;

/**
 * Created by PanShiFu 2023-07-13 15:34:49
 *
 * @author PanShiFu
 * @date 2023-07-13 15:34:49
 * @Description 会员收货地址表(MemberReceiveAddress)表数据库访问层
 */
@Mapper
public interface MemberReceiveAddressMapper extends BaseMapper<MemberReceiveAddressEntity> {

}

