package com.wwl.pojo.vo;

public class ProductInfoVo {
    //多条件查询的四个条件
    private String pName;
    private Integer lPrice;
    private Integer hPrice;
    private Integer pType;
    private Integer page=1;

    public ProductInfoVo(String pName, Integer lPrice, Integer hPrice, Integer pType, Integer page) {
        this.pName = pName;
        this.lPrice = lPrice;
        this.hPrice = hPrice;
        this.pType = pType;
        this.page = page;
    }

    public ProductInfoVo() {
    }


    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getlPrice() {
        return lPrice;
    }

    public void setlPrice(Integer lPrice) {
        this.lPrice = lPrice;
    }

    public Integer gethPrice() {
        return hPrice;
    }

    public void sethPrice(Integer hPrice) {
        this.hPrice = hPrice;
    }

    public Integer getpType() {
        return pType;
    }

    public void setpType(Integer pType) {
        this.pType = pType;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ProductInfoVo{" +
                "pName='" + pName + '\'' +
                ", lPrice=" + lPrice +
                ", hPrice=" + hPrice +
                ", pType=" + pType +
                ", page=" + page +
                '}';
    }
}
