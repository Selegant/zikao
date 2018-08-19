package com.common;

//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class PageRet<T> extends Result<List<T>> {
    /**
     * 默认的每页纪录总数，
     */
    public static Integer DEFAULT_PAGE_SIZE = 20;
    // 当前的页数
    private Integer page;
    // 当前的分页条数
    private Integer size = DEFAULT_PAGE_SIZE;
    // 总共的页数
    private Integer totalPage;
    // 总的条数
    private Long total;
    // 返回具体数据
    private List<T> body;

    public PageRet() {
    }

    public PageRet(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public PageRet(Page page) {
        if (page != null) {
            this.setBody(page.getContent());
            this.setPage(page.getNumber());
            this.setSize(page.getSize());
            this.setTotal(page.getTotalElements());
            this.setTotalPage(page.getTotalPages());
        }
    }

    /**
     * 集成beetl分页模板
     *
     * @param pageQuery
     * @Create by 2018/01/30
     * @author yansheng
     */
    public PageRet(PageQuery pageQuery) {
        if (pageQuery != null) {
            this.setBody(pageQuery.getList());
            this.setPage(new Long(pageQuery.getPageNumber()).intValue());
            this.setSize(new Long(pageQuery.getPageSize()).intValue());
            this.setTotal(pageQuery.getTotalRow());
            this.setTotalPage(new Long(pageQuery.getTotalPage()).intValue());
        }
    }

    /**
     * 计算开始行
     *
     * @return
     */
//    @ApiModelProperty(notes = "起始条数")
    public Integer getStart() {
        return (this.page - 1) * size + 1;
    }

    /**
     * 计算每页限制数
     *
     * @return
     */
//    @ApiModelProperty(notes = "结束条数")
    public Integer getEnd() {
        return this.size + getStart() - 1;
    }
}
