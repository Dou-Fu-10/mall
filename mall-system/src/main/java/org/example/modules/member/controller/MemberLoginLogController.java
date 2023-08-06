package org.example.modules.member.controller;


/**
 * Created by Dou-Fu-10 2023-07-31 15:49:05
 *
 * @author Dou-Fu-10
 * @date 2023-07-31 15:49:05
 * @Description 会员登录记录(MemberLoginLog)表控制层
 */
//@RestController
//@RequestMapping("/api/memberLoginLog")
//@Tag(name = "MemberLoginLogController", description = "")
public class MemberLoginLogController {
//    /**
//     * 服务对象
//     */
//    @Resource
//    private MemberLoginLogService memberLoginLogService;
//
//    /**
//     * 分页查询所有数据
//     *
//     * @param page              分页对象
//     * @param memberLoginLogDto 查询实体
//     * @return 所有数据
//     */
//    @AnonymousGetMapping
//    public ResponseEntity<Object> select(Page<MemberLoginLogEntity> page, MemberLoginLogDto memberLoginLogDto) {
//        return ResponseEntity.ok(this.memberLoginLogService.page(page, memberLoginLogDto));
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
//        return ResponseEntity.ok(this.memberLoginLogService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param memberLoginLogDto 实体对象
//     * @return 新增结果
//     */
//    @AnonymousPostMapping
//    public ResponseEntity<Object> insert(@RequestBody MemberLoginLogDto memberLoginLogDto) {
//        if (this.memberLoginLogService.save(memberLoginLogDto)) {
//            return ResponseEntity.ok("添加成功");
//        }
//        // 修改成自定义的 错误类型
//        throw new RuntimeException("添加失败");
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param memberLoginLogDto 实体对象
//     * @return 修改结果
//     */
//    @AnonymousPutMapping
//    public ResponseEntity<Object> update(@RequestBody MemberLoginLogDto memberLoginLogDto) {
//        if (this.memberLoginLogService.updateById(memberLoginLogDto)) {
//            return ResponseEntity.ok("修改成功");
//        }
//        // 修改成自定义的 错误类型
//        throw new RuntimeException("修改失败");
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
//        Set<Long> collect = idList.stream().filter(id -> String.valueOf(id).length() < 20 && !String.valueOf(id).isEmpty()).limit(10).collect(Collectors.toSet());
//        return ResponseEntity.ok(this.memberLoginLogService.removeByIds(collect) ? "删除成功" : "删除失败");
//    }
}

