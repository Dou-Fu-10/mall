package org.example.common.core.utils;

import cn.hutool.core.bean.BeanUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Dou-Fu-10
 * @date 2023-07-07
 * @Description 转换实用程序
 */
public class BeanCopy {
    public static <T, S> T convert(final S s, Class<T> clz) {
        // 转换
        return s == null ? null : BeanUtil.copyProperties(s, clz);
    }

    public static <T, S> List<T> copytList(List<S> s, Class<T> clz) {
        // 以流读取的方式 快速读取
        return s == null ? null : s.stream().map(vs -> BeanUtil.copyProperties(vs, clz)).collect(Collectors.toList());
    }

    public static <T, S> Set<T> copySet(Set<S> s, Class<T> clz) {
        // 以流读取的方式 快速读取
        return s == null ? null : s.stream().map(vs -> BeanUtil.copyProperties(vs, clz)).collect(Collectors.toSet());
    }
}
