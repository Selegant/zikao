package com.mapper;

import org.beetl.sql.core.mapper.MapperInvoke;
import org.beetl.sql.core.mapper.builder.MapperInvokeDataConfig;
import org.beetl.sql.core.mapper.internal.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * MapperInvoke相关 静态配置数据
 * 为了不使用户阅读代码的时候造成混乱和方便重复使用数据.
 * 独立出来的一个静态数据配置
 * </pre>
 * create time: 2017/11/18 19:18
 *
 * @author leazxl
 */
public final class MapperConfig {

    /**
     * beetlsql内置映射好的方法, 是提供给: AmiInnerProxyMapperInvoke 对象使用的.
     * 或者提供给其他自定义的BaseMapper使用
     */
    public static final Map<String, MapperInvoke> INTERNAL_AMI_METHOD = new HashMap<String, MapperInvoke>();

    static {
        // 添加内置的 INTERNAL_AMI_METHOD
        INTERNAL_AMI_METHOD.put("save", new InsertAmi());
        INTERNAL_AMI_METHOD.put("saveReturnKey", new InsertReturnKeyAmi());
        INTERNAL_AMI_METHOD.put("saveTemplate", new InsertTemplateAmi());
        INTERNAL_AMI_METHOD.put("saveBatch", new InsertBatchAmi());
        INTERNAL_AMI_METHOD.put("updateById", new UpdateByIdAmi());
        INTERNAL_AMI_METHOD.put("updateTemplateById", new UpdateTemplateByIdAmi());
        INTERNAL_AMI_METHOD.put("deleteById", new DeleteByIdAmi());
        INTERNAL_AMI_METHOD.put("get", new SingleAmi());
        INTERNAL_AMI_METHOD.put("queryList", new TemplateAmi());
        INTERNAL_AMI_METHOD.put("queryOne", new TemplateOneAmi());
        INTERNAL_AMI_METHOD.put("queryCount", new TemplateCountAmi());
        INTERNAL_AMI_METHOD.put("query", new ExecuteAmi());
        INTERNAL_AMI_METHOD.put("queryPage", new QueryPageAmi());


        MapperInvokeDataConfig.putMethodDescProxy(MethodDescExt.PAGEABLE_QUERY, new PageableMapperInvoke());
    }


}
