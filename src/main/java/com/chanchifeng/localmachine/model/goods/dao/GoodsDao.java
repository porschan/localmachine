package com.chanchifeng.localmachine.model.goods.dao;

import com.chanchifeng.localmachine.model.goods.model.Goods;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.chanchifeng.localmachine.common.staticDB.GOODS_PIC_URL;

@Repository
public class GoodsDao {

    private static Map<Integer, Goods> goodsList = null;

    static {
        goodsList = new HashMap<>();

        goodsList.put(1,new Goods().setId(1).setGoodsName("HUIWEI P30").setGoodsPic(GOODS_PIC_URL));
        goodsList.put(2,new Goods().setId(2).setGoodsName("HUIWEI P20").setGoodsPic(GOODS_PIC_URL));
        goodsList.put(3,new Goods().setId(3).setGoodsName("HUIWEI P10").setGoodsPic(GOODS_PIC_URL));
        goodsList.put(4,new Goods().setId(4).setGoodsName("HUIWEI P30 PRO").setGoodsPic(GOODS_PIC_URL));
        goodsList.put(5,new Goods().setId(5).setGoodsName("HUIWEI P20 PRO").setGoodsPic(GOODS_PIC_URL));

    }

    private static Integer initId = 6;

    public void save(Goods goods){
        if(goods.getId() == null){
            goods.setId(initId++);
        }

        goodsList.put(goods.getId(),goods);
    }

    public Collection<Goods> getAll(){
        return goodsList.values();
    }

    public Goods get(Integer id){
        return goodsList.get(id);
    }

    public void delete(Integer id){
        goodsList.remove(id);
    }

}
