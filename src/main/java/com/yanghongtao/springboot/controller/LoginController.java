package com.yanghongtao.springboot.controller;

import com.yanghongtao.springboot.entities.User;
import com.yanghongtao.springboot.mapper.UserMapper;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UserMapper userMapper;
    //登陆
    @PostMapping("/login")
    public String login(HttpSession session, String username, String password, Map<String,Object> map){
        if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){
            //查询数据库用户是否存在
            User user = userMapper.getUserByUsername(username);
            if(user != null && user.getPassword().equals(password)){
                session.setAttribute("loginUser",user);
                return "redirect:/main.html";
            }

        }
        map.put("msg","用户名或密码错误");
        return "redirect:/login.html";
    }

    //退出
    @GetMapping("/logout")
    public String logout(HttpSession session){
        //清空session中的用户信息
        session.removeAttribute("loginUser");
        //注销session
        session.invalidate();
        return "redirect:/index.html";
    }

    //修改密码页面
    @RequestMapping("/user/pwd")
    public String getUpdatePwdPage(){
        return "main/password";
    }

    @ResponseBody
    @GetMapping("/user/pwd/{oldPwd}")
    public Boolean checkPwd(HttpSession session,@PathVariable("oldPwd") String oldPwd){
        logger.info("原密码:" + oldPwd);
        User user = (User)session.getAttribute("loginUser");
        if(user.getPassword().equals(oldPwd)){
            return true;
        }
        return false;
    }

    @PostMapping("/user/pwd")
    public String updatePwd(HttpSession session, String password){
        logger.info("新密码：" + password);
        User user = (User)session.getAttribute("loginUser");
        user.setPassword(password);
        userMapper.updateUser(user);
        return "redirect:/logout";
    }
    //

}
