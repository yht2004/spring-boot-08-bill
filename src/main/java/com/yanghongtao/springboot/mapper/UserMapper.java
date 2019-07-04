package com.yanghongtao.springboot.mapper;


import com.yanghongtao.springboot.entities.User;

import java.util.List;

//@Mapper  在启动类增加@MapperScan扫描
public interface UserMapper {


    List<User> getUsers(User user);

    User getUserById(Integer id);

    int addUser(User user);

    int deleteUserById(Integer id);

    int updateUser(User user);

    User getUserByUsername(String name);

}
