package org.example.modules.tools.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.example.modules.tools.entity.HomeAdvertiseEntity;
import org.example.modules.tools.mapper.HomeAdvertiseMapper;
import org.example.modules.tools.service.HomeAdvertiseService;

/**
 * Created by PanShiFu 2023-07-13 15:39:31
 *
 * @author PanShiFu
 * @date 2023-07-13 15:39:31
 * @Description 首页轮播广告表(HomeAdvertise)表服务实现类
 */
@Service("homeAdvertiseService")
public class HomeAdvertiseServiceImpl extends ServiceImpl<HomeAdvertiseMapper, HomeAdvertiseEntity> implements HomeAdvertiseService {

}

