package org.example.modules.product.controller;


/**
 * Created by Dou-Fu-10 2023-07-14 13:54:18
 *
 * @author Dou-Fu-10
 * @date 2023-07-14 13:54:18
 * @Description 商品审核记录(ProductVertifyRecord)表控制层
 */
//@RestController
//@RequestMapping("/api/productVertifyRecord")
//@Tag(name = "ProductVertifyRecordController", description = "商品审核记录(ProductVertifyRecord)表控制层")
public class ProductVertifyRecordController {
//    /**
//     * 服务对象
//     */
//    @Resource
//    private ProductVertifyRecordService productVertifyRecordService;
//
//    /**
//     * 分页查询所有数据
//     *
//     * @param page                 分页对象
//     * @param productVertifyRecord 查询实体
//     * @return 所有数据
//     */
//    @AnonymousGetMapping
//    public ResponseEntity<Object> select(Page<ProductVertifyRecordEntity> page, ProductVertifyRecordEntity productVertifyRecord) {
//        return ResponseEntity.ok(this.productVertifyRecordService.page(page, new QueryWrapper<>(productVertifyRecord)));
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @AnonymousGetMapping("{id}")
//    public ResponseEntity<Object> selectOne(@PathVariable Serializable id) {
//        return ResponseEntity.ok(this.productVertifyRecordService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param productVertifyRecord 实体对象
//     * @return 新增结果
//     */
//    @AnonymousPostMapping
//    public ResponseEntity<Object> insert(@RequestBody ProductVertifyRecordDto productVertifyRecord) {
//        if (this.productVertifyRecordService.save(productVertifyRecord)) {
//            return ResponseEntity.ok("添加成功");
//        }
//        // 修改成自定义的 错误类型
//        throw new BaseRequestException("添加失败");
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param productVertifyRecord 实体对象
//     * @return 修改结果
//     */
//    @AnonymousPutMapping
//    public ResponseEntity<Object> update(@RequestBody ProductVertifyRecordDto productVertifyRecord) {
//        if (this.productVertifyRecordService.updateById(productVertifyRecord)) {
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
//    @AnonymousDeleteMapping
//    public ResponseEntity<Object> remove(@RequestBody Set<Long> idList) {
//        if (CollectionUtils.isEmpty(idList)) {
//            throw new BaseRequestException("请正确的填写id");
//        }
//        return ResponseEntity.ok(this.productVertifyRecordService.removeByIds(idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet())) ? "删除成功" : "删除失败");
//    }
}

