package com.chanchifeng.localmachine.model.goods.handler;

import com.chanchifeng.localmachine.model.goods.dao.GoodsDao;
import com.chanchifeng.localmachine.model.goods.model.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class GoodsHandler {

    @Autowired
    private GoodsDao goodsDao;

    @RequestMapping(value = "/allgoods", method = RequestMethod.GET)
    public Map<String, Object> getAll() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("allgoods", goodsDao.getAll());
        return map;
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public Map<String, Object> input(@PathVariable("id") Integer id) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("user", goodsDao.get(id));
        return map;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    public Map<String, Object> save(@Valid Goods goods, Errors result) {
        Map<String, Object> map = new LinkedHashMap<>();
        System.out.println("save: " + goods);

        if (result.getErrorCount() > 0) {
            System.out.println("出错了!");

            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }

            //若验证出错, 则转向定制的页面
            map.put("state", "fail");

            return map;
        }

        goodsDao.save(goods);
        map.put("state", "success");
        return map;
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        Map<String, Object> map = new LinkedHashMap<>();
        goodsDao.delete(id);
        map.put("state", "success");
        return map;
    }


    @RequestMapping(value = "/goods", method = RequestMethod.PUT)
    public Map<String, Object> update(Goods goods) {
        System.out.println(goods.toString());
        goodsDao.save(goods);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("state", "success");
        return map;
    }
}
