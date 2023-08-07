package org.example.modules.product.controller;


/**
 * Created by Dou-Fu-10 2023-07-14 13:54:17
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:17
 * @Description 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表控制层
 */
//@RestController
//@RequestMapping("/api/productCategoryAttributeRelation")
//@Tag(name = "ProductCategoryAttributeRelationController", description = "产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）(ProductCategoryAttributeRelation)表控制层")
public class ProductCategoryAttributeRelationController {
//    /**
//     * 服务对象
//     */
//    @Resource
//    private ProductCategoryAttributeRelationService productCategoryAttributeRelationService;
//
//    /**
//     * 分页查询所有数据
//     *
//     * @param page                             分页对象
//     * @param productCategoryAttributeRelation 查询实体
//     * @return 所有数据
//     */
//    @GetMapping
//    @Operation(summary = "分页查询所有数据", description = "productCategoryAttributeRelation::select")
//    @PreAuthorize("@hasPermission.check('productCategoryAttributeRelation::select')")
//    public ResponseEntity<Object> select(Page<ProductCategoryAttributeRelationEntity> page, ProductCategoryAttributeRelationEntity productCategoryAttributeRelation) {
//        return ResponseEntity.ok(this.productCategoryAttributeRelationService.page(page, new QueryWrapper<>(productCategoryAttributeRelation)));
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    @Operation(summary = "通过主键查询单条数据", description = "productCategoryAttributeRelation::selectOne")
//    @PreAuthorize("@hasPermission.check('productCategoryAttributeRelation::selectOne')")
//    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
//        return ResponseEntity.ok(this.productCategoryAttributeRelationService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param productCategoryAttributeRelation 实体对象
//     * @return 新增结果
//     */
//    @PostMapping
//    @Operation(summary = "新增数据", description = "productCategoryAttributeRelation::insert")
//    @PreAuthorize("@hasPermission.check('productCategoryAttributeRelation::insert')")
//    public ResponseEntity<Object> insert(@RequestBody ProductCategoryAttributeRelationDto productCategoryAttributeRelation) {
//        if (this.productCategoryAttributeRelationService.save(productCategoryAttributeRelation)) {
//            return ResponseEntity.ok("添加成功");
//        }
//        // 修改成自定义的 错误类型
//        throw new BaseRequestException("添加失败");
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param productCategoryAttributeRelation 实体对象
//     * @return 修改结果
//     */
//    @PutMapping
//    @Operation(summary = "修改数据", description = "productCategoryAttributeRelation::update")
//    @PreAuthorize("@hasPermission.check('productCategoryAttributeRelation::update')")
//    public ResponseEntity<Object> update(@RequestBody ProductCategoryAttributeRelationDto productCategoryAttributeRelation) {
//        if (this.productCategoryAttributeRelationService.updateById(productCategoryAttributeRelation)) {
//            return ResponseEntity.ok("修改成功");
//        }
//        // 修改成自定义的 错误类型
//        throw new BaseRequestException("修改失败");
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param idList 主键结合
//     * @return 删除结果
//     */
//    @DeleteMapping
//    @Operation(summary = "删除数据", description = "productCategoryAttributeRelation::remove")
//    @PreAuthorize("@hasPermission.check('productCategoryAttributeRelation::remove')")
//    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
//        if (CollectionUtils.isEmpty(idList)) {
//            throw new BaseRequestException("请正确的填写id");
//        }
//        Set<Long> ids = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
//        if (this.productCategoryAttributeRelationService.removeByIds(ids)) {
//            return ResponseEntity.ok("删除成功");
//        }
//        throw new BaseRequestException("删除失败");
//    }
}

