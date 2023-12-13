package com.buercorp.appdemo.portalapi.controller;

import com.buercorp.appdemo.portalapi.interceptor.annotation.LocaleRequired;
import com.buercorp.appdemo.portalapi.interceptor.annotation.LoginRequired;
import com.buercorp.appdemo.repository.model.dto.LoginDto;
import com.buercorp.appdemo.repository.model.po.User;
import com.buercorp.appdemo.repository.model.vo.LoginVo;
import com.buercorp.appdemo.repository.model.vo.UserInfoVo;
import com.buercorp.appdemo.service.user.UserService;
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
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户登陆
     * @param loginDto
     * @return
     */

    @PostMapping(value = "/login")
    public LoginVo login(@RequestBody @Validated LoginDto loginDto){
        LoginVo loginVo = userService.login(loginDto);
        return loginVo;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */

    @LocaleRequired
    @LoginRequired
    @GetMapping(value = "/getUserInfo")
    public UserInfoVo getUserInfo(@RequestHeader(name = "token") String token){
        UserInfoVo userInfo = userService.getUserInfo(token);
        return userInfo;
    }

    /**
     * 新增用户
     * @param user
     */
    @LoginRequired
    @PostMapping(value = "/saveUser")
    public void saveUser(@RequestBody @Validated User user){
        userService.saveUser(user);
    }

    /**
     * 修改用户信息
     * @param user
     */
    @LoginRequired
    @PutMapping(value = "/updateSysUser")
    public void updateSysUser(@RequestBody @Validated User user) {
        userService.updateUser(user);
    }

    /**
     * 删除用户信息
     * @param id
     */
    @LoginRequired
    @DeleteMapping(value = "/deleteUserById/{userId}")
    public void deleteUserById(@PathVariable(value = "userId") Long id){
        userService.deleteById(id);
    }
}