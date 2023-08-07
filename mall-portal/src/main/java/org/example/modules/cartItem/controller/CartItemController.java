package org.example.modules.cartItem.controller;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.example.common.core.exception.BaseRequestException;
import org.example.modules.cartItem.entity.CartItemEntity;
import org.example.modules.cartItem.entity.dto.AddCartItemDto;
import org.example.modules.cartItem.entity.dto.CartItemDto;
import org.example.modules.cartItem.serveice.CartItemService;
import org.example.security.annotaion.rest.AnonymousDeleteMapping;
import org.example.security.annotaion.rest.AnonymousGetMapping;
import org.example.security.annotaion.rest.AnonymousPostMapping;
import org.example.security.utils.SecurityUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Dou-Fu-10 2023-07-14 14:35:11
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 14:35:11
 * @Description 购物车表(CartItem)表控制层
 */
@RestController
@RequestMapping("/app/cart")
@Tag(name = "CartItemController", description = "购物车表(CartItem)表控制层")
public class CartItemController {
    /**
     * 服务对象
     */
    @Resource
    private CartItemService cartItemService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @return 所有数据
     */
    @AnonymousGetMapping
    public ResponseEntity<Object> select(Page<CartItemEntity> page) {
        return ResponseEntity.ok(this.cartItemService.page(page));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @AnonymousGetMapping("{id}")
    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
        return ResponseEntity.ok(this.cartItemService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param cartItem 实体对象
     * @return 新增结果
     */
    @AnonymousPostMapping
    public ResponseEntity<String> insert(@RequestBody AddCartItemDto cartItem) {
        if (this.cartItemService.save(cartItem)) {
            return ResponseEntity.ok("添加成功");
        }
        // 修改成自定义的 错误类型
        throw new BaseRequestException("添加失败");
    }

    /**
     * 修改数据
     *
     * @param cartItem 实体对象
     * @return 修改结果
     */
//    @AnonymousPutMapping
    public ResponseEntity<String> update(@RequestBody CartItemDto cartItem) {
        if (this.cartItemService.updateById(cartItem)) {
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
    @AnonymousDeleteMapping
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BaseRequestException("请正确的填写id");
        }
        idList = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
        List<CartItemEntity> cartItemEntityList = idList.stream().map(id -> new CartItemEntity(id, SecurityUtils.getCurrentUserId())).collect(Collectors.toList());
        if (this.cartItemService.removeByIds(cartItemEntityList)) {
            return ResponseEntity.ok("删除成功");
        }
        throw new BaseRequestException("删除失败");
    }
}

