package com.la.eduservice.controller;

import com.la.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
@Api(description="登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduloginController {
    //login
    @PostMapping("login")
    public R login(){

        return R.ok().data("token","admin");

    }
    //info
    @GetMapping("info")
    public  R info(){

        return R.ok().data("name","admin").data("avatar",
                "https://edu-online020.oss-cn-guangzhou.aliyuncs.com/0.jpg");
    }



}
