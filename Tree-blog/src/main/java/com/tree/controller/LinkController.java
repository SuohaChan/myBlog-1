package com.tree.controller;

import com.tree.annotation.mySystemLog;
import com.tree.domain.Link;
import com.tree.domain.ResponseResult;
import com.tree.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    @mySystemLog(xxbusinessName = "获取友链")
    public ResponseResult getAllLink() {
        return linkService.getAllLink();
    }
}
