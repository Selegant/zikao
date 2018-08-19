package com.orm;

import com.common.PageRet;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseDao<T> extends BaseMapper<T> {

    /**
     * 通用插入，插入一个实体对象到数据库，所以字段将参与操作，除非你使用ColumnIgnore注解
     *
     * @param entity
     */
    void save(T entity);

    /**
     * （数据库表有自增主键调用此方法）如果实体对应的有自增主键，插入一个实体到数据库，设置assignKey为true的时候，将会获取此主键
     *
     * @param entity
     * @param autDbAssignKey 是否获取自增主键
     */
    void save(T entity, boolean autDbAssignKey);

    /**
     * 插入实体到数据库，对于null值不做处理
     *
     * @param entity
     */
    void saveTemplate(T entity);

    /**
     * 如果实体对应的有自增主键，插入实体到数据库，对于null值不做处理,设置assignKey为true的时候，将会获取此主键
     *
     * @param entity
     * @param autDbAssignKey
     */
    void saveTemplate(T entity, boolean autDbAssignKey);

    /**
     * 批量插入实体。此方法不会获取自增主键的值，如果需要，建议不适用批量插入，适用
     * <pre>
     * insert(T entity,true);
     * </pre>
     *
     * @param list
     */
    void saveBatch(List<T> list);

    /**
     * （数据库表有自增主键调用此方法）如果实体对应的有自增主键，插入实体到数据库，自增主键值放到keyHolder里处理
     *
     * @param entity
     * @return
     */
    KeyHolder saveReturnKey(T entity);

    /**
     * 根据主键获取对象，如果对象不存在，返回null
     *
     * @param key
     * @return
     */
    T get(Object key);

    /**
     * 模板查询，返回一条结果,如果没有，返回null
     *
     * @param entity
     * @return
     */
    <T> T queryOne(T entity);

    /**
     * 模板查询，返回符合模板得所有结果。beetlsql将取出非null值（日期类型排除在外），从数据库找出完全匹配的结果集
     *
     * @param entity
     * @return
     */
    List<T> queryList(T entity);

    List<T> queryList(T entity, int start, int size);

    /**
     * 符合模板得个数
     *
     * @param entity
     * @return
     */
    long queryCount(T entity);


    /**
     * 执行一个jdbc sql模板查询
     *
     * @param sql
     * @param args
     * @return
     */
    List<T> query(String sql, Object... args);

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    PageRet<T> queryPage(T t, Pageable pageable);
}
