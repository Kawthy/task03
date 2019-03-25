
package cn.ki.controller;

import cn.ki.pojo.User;
import cn.ki.serviceimpl.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈登录管理〉
 *
 * @author Kawthy
 * @create 2019/3/19
 * @since 1.0.0
 */

@Controller
public class LoginController {
    private static Log log = LogFactory.getLog(LoginController.class);

    @Autowired
    UserService userService;

    //登录
    @RequestMapping(value = "/a",method = RequestMethod.POST)
    public String login(Model model, @Param("username")String name,@Param("password")String password){
        log.info("进入根据id查询用户========");
        log.info("name======"+name);
        log.info("password======"+password);
        //            判断用户名不能为空,且只能由数字,小写大写字母组成
        if(name.matches("[a-zA-Z0-9]+") && name!=null){
            //通过用户名查询用户
            User user =userService.selectByName(name);
            //判断用户是否为空(即能否从数据库中找到)
            if(user != null){
                log.info("user======"+user);
                //判断用户密码是否正确
                if(user.getPassword().equals(password)){
                    log.info("user.getPassword()======"+user.getPassword());
                    //返回登录后页面
                    model.addAttribute("code", 1000);
                    model.addAttribute("message", "查看成功");
                    return "user";
                }else{
                    log.error("密码错误");
                }
            }else{
                log.error("找不到用户");
            }
            log.error("用户名为空");
        }
       return "error";
    }
}