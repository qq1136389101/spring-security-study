package com.github.weichun97.sms.controller;

import cn.hutool.json.JSONUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chun
 * @date 2020/8/27 18:12
 */
@RestController
public class IndexController {

    @GetMapping("")
    public String index(){
        return JSONUtil.toJsonStr(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
