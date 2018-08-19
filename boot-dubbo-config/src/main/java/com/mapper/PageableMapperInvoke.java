package com.mapper;

import com.common.PageRet;
import com.utils.BeanUtils;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.MapperInvoke;
import org.beetl.sql.core.mapper.MethodDesc;
import org.springframework.data.domain.Pageable;

import javax.persistence.Table;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiandafu
 */
public class PageableMapperInvoke implements MapperInvoke {

    @Override
    public Object call(SQLManager sm, Class entityClass, String sqlId, Method m, Object[] args) {
        MethodDesc desc = MethodDesc.getMetodDesc(sm, entityClass, m, sqlId);
        Class returnType = m.getReturnType();
        PageQuery<Object> param = getParam(m, args);
        sm.pageQuery(sqlId, desc.resultType, param);
        PageRet<Object> pageRet = new PageRet<>(param);
        if (returnType == PageRet.class) {
            return pageRet;
        } else {
            return null;
        }
    }


    private PageQuery<Object> getParam(Method m, Object[] args) {
        PageQuery<Object> query = null;
        Map<String, Object> map = new HashMap<>(args.length);
        Parameter[] p = m.getParameters();
        for (int i = 0; i < p.length; i++) {
            Parameter pp = p[i];
            if (pp.getType() == Pageable.class) {
                Pageable page = (Pageable) args[i];
                query = new PageQuery<>(page.getPageNumber(), page.getPageSize());
                continue;
            }
            Param a = pp.getAnnotation(Param.class);
            if (a != null) {
                map.put(a.value(), args[i]);
                continue;
            }
            if (pp.getType().getAnnotation(Table.class) != null ||
                    pp.getType().getAnnotation(org.beetl.sql.core.annotatoin.Table.class) != null) {
                map.putAll(BeanUtils.beanToMap(args[i]));
            }
        }
        if (query == null) {
//            throw new BaseException("990010", "参数口没有分页对象" + m.getName());
        }
        query.setParas(map);
        return query;
    }
}
