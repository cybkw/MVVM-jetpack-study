package com.bkw.study.data.home;

import java.util.List;

public class PageVO<T> {
    /**
     * curPage : 2
     * datas :
     * offset : 20
     * over : false
     * pageCount : 415
     * size : 20
     * total : 8295
     */

    private int curPage;
    private List<T> datas;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;

    public PageVO() {
    }


    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
