package com.github.weichun97.permission.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chun
 * @date 2020/8/27 18:12
 */
@RestController
public class IndexController {

    @GetMapping("/no-permission")
    public String noPermission(){
        return "无权限";
    }

    @GetMapping("")
    public String index(){
        return "首页";
    }


    @GetMapping("/auth/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String authenticationTest() {
        return "您拥有admin权限，可以查看";
    }
}
