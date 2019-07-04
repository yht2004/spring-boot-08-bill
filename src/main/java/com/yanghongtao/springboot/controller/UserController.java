package com.yanghongtao.springboot.controller;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.yanghongtao.springboot.entities.User;
import com.yanghongtao.springboot.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserMapper userMapper;

    //查询用户
    @GetMapping("/users")
    public String list(Map<String,Object> map, User user){
        logger.info("用户列表:"+user);
        List<User> users = userMapper.getUsers(user);

        map.put("users",users);
        map.put("realName",user.getRealName());

        return "user/list";
    }

    //用户详情
    @GetMapping("/user/{id}")
    public String userInfo(@RequestParam(value = "type",defaultValue = "view") String type, @PathVariable("id") Integer id, Map<String, Object> map){
        User user = userMapper.getUserById(id);

        if(type.equals("update")){
            List<User> users = userMapper.getUsers(null);
            map.put("users",users);
        }
        map.put("user",user);

        return "user/" + type;
    }

    //用户修改
    @PutMapping("/user")
    public String updateUser(User user){
        userMapper.updateUser(user);
        return "redirect:users";
    }

    //进入添加用户页面
    @GetMapping("/user")
    public String addView(){

        return "user/add";
    }

    //添加用户
    @PostMapping("/user")
    public String addUser(User user){
        userMapper.addUser(user);
        return "redirect:/users";
    }

    //删除用户
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userMapper.deleteUserById(id);

        return "redirect:/users";
    }

}
