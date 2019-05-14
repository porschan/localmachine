package com.chanchifeng.localmachine.model.user.dao;

import com.chanchifeng.localmachine.model.user.model.Department;
import com.chanchifeng.localmachine.model.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {

    private static Map<Integer, User> Users = null;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        Users = new HashMap<Integer, User>();

        Users.put(1001, new User(1001, "E-AA", "aa@163.com", 1, new Department(101, "D-AA")));
        Users.put(1002, new User(1002, "E-BB", "bb@163.com", 1, new Department(102, "D-BB")));
        Users.put(1003, new User(1003, "E-CC", "cc@163.com", 0, new Department(103, "D-CC")));
        Users.put(1004, new User(1004, "E-DD", "dd@163.com", 0, new Department(104, "D-DD")));
        Users.put(1005, new User(1005, "E-EE", "ee@163.com", 1, new Department(105, "D-EE")));
    }

    private static Integer initId = 1006;

    public void save(User user){
        if(user.getId() == null){
            user.setId(initId++);
        }

        user.setDepartment(departmentDao.getDepartment(user.getDepartment().getId()));
        Users.put(user.getId(), user);
    }

    public Collection<User> getAll(){
        return Users.values();
    }

    public User get(Integer id){
        return Users.get(id);
    }

    public void delete(Integer id){
        Users.remove(id);
    }
    
}
