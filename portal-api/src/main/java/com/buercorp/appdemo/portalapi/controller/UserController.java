package com.buercorp.appdemo.portalapi.controller;

import com.buercorp.appdemo.portalapi.interceptor.annotation.LocaleRequired;
import com.buercorp.appdemo.portalapi.interceptor.annotation.LoginRequired;
import com.buercorp.appdemo.portalapi.interceptor.annotation.NotLogin;
import com.buercorp.appdemo.portalapi.utils.RequestComponent;
import com.buercorp.appdemo.repository.model.dto.LoginDto;
import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.repository.model.vo.LoginVo;
import com.buercorp.appdemo.repository.model.vo.UserInfoVo;
import com.buercorp.appdemo.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description user - 请求处理
 *
 * @author tanghx
 * @date 2023/12/4 10:06
 */

@Slf4j
@LoginRequired      // 登陆拦截
@LocaleRequired     // 语言环境拦截
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestComponent requestComponent;

    /**
     * 用户登陆
     * @param loginDto
     * @return
     */
    @NotLogin       // 不拦截登陆请求
    @PostMapping(value = "/login")
    public LoginVo login(@RequestBody @Validated LoginDto loginDto){
        StringBuffer requestURL = requestComponent.getRequest().getRequestURL();
        LoginVo loginVo = userService.login(loginDto, requestURL);
        return loginVo;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */


    @GetMapping(value = "/getUserInfo")
    public UserInfoVo getUserInfo(@RequestHeader(name = "token") String token){
        StringBuffer requestURL = requestComponent.getRequest().getRequestURL();
        UserInfoVo userInfo = userService.getUserInfo(token, requestURL);
        return userInfo;
    }

    /**
     * 新增用户
     * @param user
     */
    @PostMapping(value = "/saveUser")
    public void saveUser(@RequestBody @Validated User user){
        userService.saveUser(user);
    }

    /**
     * 修改用户信息
     * @param user
     */
    @PutMapping(value = "/updateSysUser")
    public void updateSysUser(@RequestBody @Validated User user) {
        userService.updateUser(user);
    }

    /**
     * 删除用户信息
     * @param id
     */
    @DeleteMapping(value = "/deleteUserById/{userId}")
    public void deleteUserById(@PathVariable(value = "userId") Long id){
        userService.deleteById(id);
    }
}