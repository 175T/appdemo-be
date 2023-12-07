package com.tang.appdemo.protoApi.controller;

import com.tang.appdemo.protoApi.interceptor.annotation.LoginRequired;
import com.tang.appdemo.repository.model.dto.LoginDto;
import com.tang.appdemo.repository.model.vo.LoginVo;
import com.tang.appdemo.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 登陆请求处理
 *
 * @author tanghx
 * @date 2023/12/4 10:06
 */

@Slf4j
@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    @LoginRequired
    public LoginVo login(@RequestBody LoginDto loginDto){
        LoginVo loginVo = loginService.login(loginDto);
        return loginVo;
    }
}