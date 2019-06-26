package com.myicellar.digitalmenu.vo.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class PageRequestVO implements Serializable {
    /**
     * 第几页
     */
    protected int pageNumber = 1;
    /**
     * 每页条数
     */
    protected int pageSize = 20;

    public int getOffset() {
        return (this.pageNumber - 1) * this.pageSize;
    }
}
