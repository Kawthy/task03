/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: UserInterceptor
 * Author:   Kawthy
 * Date:     2019/3/19 14:34
 * Description: 拦截器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者名称           修改时间           版本号              描述
 */
package cn.ki.interceptor;

import cn.ki.pojo.Character;
import cn.ki.pojo.Purview;
import cn.ki.pojo.PurviewExample;
import cn.ki.pojo.User;
import cn.ki.serviceimpl.CharacterService;
import cn.ki.serviceimpl.PurviewService;
import cn.ki.serviceimpl.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈拦截器〉
 *
 * @author Kawthy
 * @create 2019/3/19
 * @since 1.0.0
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static Log log = LogFactory.getLog(LoginInterceptor.class);

    @Autowired
    UserService userService;

    @Autowired
    CharacterService characterService;

    @Autowired
    PurviewService purviewService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        log.info("hello pre====");
        log.info("拦截器1,程序进入preHandle方法,拦截登录用户返回权限到页面,");
        String uid=request.getHeader("uid");
        //判断是否为空
        if(uid!=null && uid.matches("[0-9]+")){
            log.info("uid================"+uid);
            //通过uid查询用户
           User user = userService.selectByPrimaryKey(Long.valueOf(uid));
            log.info("user================"+user);
           //判断非空
           if(null != user){
            //通过user的角色id查询角色
            Character character = characterService.selectByPrimaryKey(user.getCharacter());
               log.info("character================"+character);
            if(null !=character){
                //获取角色所存储的权限字段.
                String Cpurview = character.getPurview();
                //存储登录id
                request.setAttribute("id",uid);
                //打印登录者ID
                log.info("uid================"+uid);
                //存储登录id所拥有权限
                request.setAttribute("purview",Cpurview);
                //打印登录id所拥有权限
                log.info("purview================"+uid);
                return true;
            }
            log.error("通过user查询角色失败,角色为空");
           }
           log.error("通过uid查询用户失败,user为空");
        }
        log.error("获取uid失败");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("hello post====");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("hello after====");
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("hello after con====");
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}