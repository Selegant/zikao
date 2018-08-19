package com.mapper;

import com.common.PageRet;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.mapper.MapperInvoke;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 用于单表简单翻页查询
 *
 * @author leazxl
 */
public class QueryPageAmi implements MapperInvoke {

    @Override
    public Object call(SQLManager sm, Class entityClass, String sqlId, Method m, Object[] args) {
        PageRet<Object> pageRet = new PageRet<>();
        Object params = args[0];
        Pageable query = (Pageable) args[1];
        if (pageRet.getTotal() == null || pageRet.getTotal() < 0) {
            pageRet.setTotal(sm.templateCount(entityClass, params));
        }
        long start = (sm.isOffsetStartZero() ? 0 : 1) + (query.getPageNumber() - 1) * query.getPageSize();
        long size = query.getPageSize();

        List<Object> list = sm.template(entityClass, params, start, size);
        pageRet.setBody(list);
        pageRet.setPage(query.getPageNumber());
        pageRet.setSize(query.getPageSize());
        return pageRet;

    }

}
