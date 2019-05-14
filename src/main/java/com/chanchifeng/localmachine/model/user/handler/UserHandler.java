package com.chanchifeng.localmachine.model.user.handler;

import com.chanchifeng.localmachine.model.user.dao.DepartmentDao;
import com.chanchifeng.localmachine.model.user.dao.UserDao;
import com.chanchifeng.localmachine.model.user.model.User;
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
public class UserHandler {

    @Autowired
    private UserDao userDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public Map<String, Object> update(User user) {
        System.out.println(user.toString());
        userDao.save(user);
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("state", "success");
        return map;
    }

    /**
     * 获取单个用户信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Map<String, Object> input(@PathVariable("id") Integer id) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("user", userDao.get(id));
        map.put("departments", departmentDao.getDepartments());
        return map;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> delete(@PathVariable("id") Integer id) {
        Map<String, Object> map = new LinkedHashMap<>();
        userDao.delete(id);
        map.put("state", "success");
        return map;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Map<String, Object> save(@Valid User user, Errors result) {
        Map<String, Object> map = new LinkedHashMap<>();
        System.out.println("save: " + user);

        if (result.getErrorCount() > 0) {
            System.out.println("出错了!");

            for (FieldError error : result.getFieldErrors()) {
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }

            //若验证出错, 则转向定制的页面
            map.put("state", "fail");

            return map;
        }

        userDao.save(user);
        map.put("state", "success");
        return map;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Map<String, Object> getAll() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("departments", departmentDao.getDepartments());
        map.put("user", userDao.getAll());
        return map;
    }

}
