package com.batman.gexinoauth2.common.request;

/**
 * 前后端交互请求的抽象类.
 *
 */
public abstract class MetaRestRequest implements StoreAware {

    private static final long serialVersionUID = 2933062658944790065L;

    private static final Integer DEFAULT_PAGE_SIZE = 20;

    private static final Integer DEFAULT_PAGE_NUMBER = 1;

    private Integer pageNumber = DEFAULT_PAGE_NUMBER;

    private Integer pageSize = DEFAULT_PAGE_SIZE;

    private Integer offset;

    private Object store;

    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * 设置页码.
     *
     * @param pageNumber
     */
    public void setPageNumber(Integer pageNumber) {
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 1;
        }
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public Object getStore() {
        return store;
    }

    @Override
    public void setStore(Object object) {
        this.store = object;
    }

    /**
     * 偏移量，第几条记录.
     *
     * @return
     */
    public Integer getOffset() {
        this.offset = (this.pageNumber - 1) * this.pageSize;
        return offset;
    }

}
