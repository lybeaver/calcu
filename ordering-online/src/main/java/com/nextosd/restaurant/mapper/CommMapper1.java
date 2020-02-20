package com.nextosd.restaurant.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CommMapper1 {
	/** * 保存 * @param tableMap */
    public void save(@Param("tableMap") Map<Object, Map<Object, Object>> tableMap);

    /** * 更新 * @param tableMap */
    public void update(@Param("tableMap") Map<Object, Map<Object, Object>> tableMap);

    /** * 删除 * @param tableMap */
    public void delete(@Param("tableMap") Map<Object, Map<Object, Object>> tableMap);

    /** * 查询 * @param <T> * @param tableMap */
    public List<Map<String,Object>> query(@Param("tableMap") Map<Object, Map<Object, Object>> tableMap);
}
