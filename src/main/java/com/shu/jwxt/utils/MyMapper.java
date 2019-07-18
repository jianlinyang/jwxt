package com.shu.jwxt.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author yang
 * @date 2019/7/18 13:35
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
