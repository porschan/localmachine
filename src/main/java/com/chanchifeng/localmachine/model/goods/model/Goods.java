package com.chanchifeng.localmachine.model.goods.model;

import javax.validation.constraints.NotEmpty;

public class Goods {

    private Integer id;
    @NotEmpty
    private String goodsName;
    private String goodsPic;

    public Goods(){

    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPic='" + goodsPic + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Goods setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public Goods setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public Goods setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
        return this;
    }
}
