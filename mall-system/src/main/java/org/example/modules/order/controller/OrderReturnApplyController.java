package org.example.modules.order.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.common.core.utils.BeanCopy;
import org.example.modules.order.entity.OrderReturnApplyEntity;
import org.example.modules.order.entity.dto.OrderReturnApplyDto;
import org.example.modules.order.entity.vo.OrderReturnApplyVo;
import org.example.modules.order.service.OrderReturnApplyService;
import org.example.modules.tools.entity.CompanyAddressEntity;
import org.example.modules.tools.entity.vo.CompanyAddressVo;
import org.example.modules.tools.service.CompanyAddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Dou-Fu-10 2023-07-14 14:34:30
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:34:30
 * @Description 订单退货申请(OrderReturnApply)表控制层
 */
@RestController
@RequestMapping("/api/orderReturnApply")
@Tag(name = "OrderReturnApplyController", description = "订单退货申请(OrderReturnApply)表控制层")
public class OrderReturnApplyController {
    /**
     * 服务对象
     */
    @Resource
    private OrderReturnApplyService orderReturnApplyService;

    @Resource
    private CompanyAddressService companyAddressService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param orderReturnApply 查询实体
     * @return 所有数据
     */
    @Operation(summary = "分页查询所有数据", description = "orderReturnApply::select")
    @GetMapping
    @PreAuthorize("@hasPermission.check('orderReturnApply::select')")
    public ResponseEntity<Object> select(Page<OrderReturnApplyEntity> page, OrderReturnApplyDto orderReturnApply) {
        return ResponseEntity.ok(this.orderReturnApplyService.page(page, orderReturnApply));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据", description = "orderReturnApply::selectOne")
    @GetMapping("{id}")
    @PreAuthorize("@hasPermission.check('orderReturnApply::selectOne')")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        if (Objects.isNull(id)) {
            return ResponseEntity.ok("未获取到相应的订单退货");
        }
        OrderReturnApplyEntity orderReturnApplyEntity = this.orderReturnApplyService.getById(id);
        if (Objects.isNull(orderReturnApplyEntity)) {
            return ResponseEntity.ok("未获取到相应的订单退货");
        }
        OrderReturnApplyVo orderReturnApplyVo = BeanCopy.convert(orderReturnApplyEntity, OrderReturnApplyVo.class);
        CompanyAddressEntity companyAddressEntity = companyAddressService.getById(orderReturnApplyVo.getCompanyAddressId());
        CompanyAddressVo companyAddressVo = BeanCopy.convert(companyAddressEntity, CompanyAddressVo.class);
        // 退货地址
        orderReturnApplyVo.setCompanyAddress(companyAddressVo);
        return ResponseEntity.ok(orderReturnApplyVo);
    }

    /**
     * 新增数据
     *
     * @param orderReturnApply 实体对象
     * @return 新增结果
     */
//    @AnonymousPostMapping
//    public ResponseEntity<Object> insert(@RequestBody OrderReturnApplyDto orderReturnApply) {
//        if (this.orderReturnApplyService.save(orderReturnApply)) {
//            return ResponseEntity.ok("添加成功");
//        }
//        // 修改成自定义的 错误类型
//        throw new BaseRequestException("添加失败");
//    }

    /**
     * 修改数据
     *
     * @param orderReturnApply 实体对象
     * @return 修改结果
     */
    @Operation(summary = "修改数据", description = "orderReturnApply::update")
    @PutMapping
    @PreAuthorize("@hasPermission.check('orderReturnApply::update')")
    public ResponseEntity<Object> update(@RequestBody OrderReturnApplyDto orderReturnApply) {
        if (this.orderReturnApplyService.updateById(orderReturnApply)) {
            return ResponseEntity.ok("修改成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("修改失败");
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
//    @AnonymousDeleteMapping
//    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
//        if (CollectionUtils.isEmpty(idList)) {
//            throw new BaseRequestException("请正确的填写id");
//        }
//        return ResponseEntity.ok(this.orderReturnApplyService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败");
//    }
}

