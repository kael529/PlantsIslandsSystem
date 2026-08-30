package com.zhiyu.plantsislandssystem.controller;

import com.zhiyu.plantsislandssystem.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping("/hello")
    public Result<String> hello(){
        return Result.success("🍃植屿后端启动成功!");
    }
}
