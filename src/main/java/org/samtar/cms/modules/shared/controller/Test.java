package org.samtar.cms.modules.shared.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/testing")
public class Test {
    @GetMapping("/health")
    public String healthTest(){
        return  "Working fine";
    }

}
