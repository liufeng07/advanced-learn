package com.itfeng.frame.elk.controller;

import com.itfeng.frame.elk.common.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping
    public R<String> addLog(@RequestParam(value = "param1",required = false) String param1){
        return R.success("你好，这段话将被日志记录");
    }
}
