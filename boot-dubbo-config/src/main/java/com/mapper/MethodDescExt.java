package com.mapper;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.mapper.MethodDesc;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class MethodDescExt extends MethodDesc {
    public final static int PAGEABLE_QUERY = 1000;

    private Method method = null;

    @Override
    protected void doParse(SQLManager sm, Class entityClass, Method m, String sqlId) {
        Class[] paras = m.getParameterTypes();
        Type retType = m.getGenericReturnType();
        //假设默认类型就是Mapper的泛型类型
        this.resultType = entityClass;
        this.method = m;
        super.doParse(sm, entityClass, m, sqlId);
    }

    @Override
    protected void parseSelectList(Class[] paras, Type retType) {
        boolean pageType = false;
        if (paras != null) {
            for (Class c : paras) {
                if (c.getName().equals(Pageable.class.getName())) {
                    pageType = true;
                    break;
                }
            }
        }
        if (pageType) {
            this.type = PAGEABLE_QUERY;
            //  parameter = new PageQueryParamter(method, super.paramsDeclare, false);
        } else {
            super.parseSelectList(paras, retType);
        }
    }
}
