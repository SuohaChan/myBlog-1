package com.tree.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 35238
 * @date 2023/7/20 0020 21:37
 */
//页面 行(article) 总数
public class PageVo {

    private List rows;
    private Long total;

    public PageVo() {
    }

    public PageVo(List rows, Long total) {
        this.rows = rows;
        this.total = total;
    }


    /**
     * 获取
     * @return rows
     */
    public List getRows() {
        return rows;
    }

    /**
     * 设置
     * @param rows
     */
    public void setRows(List rows) {
        this.rows = rows;
    }

    /**
     * 获取
     * @return total
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 设置
     * @param total
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    public String toString() {
        return "PageVo{rows = " + rows + ", total = " + total + "}";
    }

}