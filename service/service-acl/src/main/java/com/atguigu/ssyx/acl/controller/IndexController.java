package com.atguigu.ssyx.acl.controller;

import com.atguigu.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录接口")
@RestController
@RequestMapping("/admin/acl/index")
@CrossOrigin
public class IndexController {

    //1 login登录
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<Map<String, String>> login() {
        //返回token值
        Map<String, String> map = new HashMap<>();
        map.put("token", "token-admin");
        return Result.ok(map);
    }

    //2 getInfo获取信息
    @ApiOperation(value = "获取信息")
    @GetMapping("/info")
    public Result<Map<String, String>> info() {
        //返回用户信息
        Map<String, String> map = new HashMap<>();
        map.put("name", "admin");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }

    //3 logout退出
    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public Result<?> logout() {
        return Result.ok();
    }
}
