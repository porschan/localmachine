package com.chanchifeng.localmachine.model.page.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PageHandler {

    @GetMapping("/")
    public ModelAndView index(String param) {
        System.out.println("this");
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

}
