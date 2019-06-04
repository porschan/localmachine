package com.chanchifeng.localmachine.model.page.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageHandler {

    @GetMapping("/")
    public ModelAndView index(String param) {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/page/restPage")
    public ModelAndView restPage(String param) {
        ModelAndView modelAndView = new ModelAndView("restPage");
        return modelAndView;
    }

    @GetMapping("/page/goodsPage")
    public ModelAndView goodsPage(String param) {
        ModelAndView modelAndView = new ModelAndView("goodsPage");
        return modelAndView;
    }

    @GetMapping("/page/filePage")
    public ModelAndView filePage(@RequestParam(value = "message",required = false) String param) {
        ModelAndView modelAndView = new ModelAndView("filePage");
        modelAndView.addObject("message",param);
        return modelAndView;
    }

    @GetMapping("/page/socketPage")
    public ModelAndView socketPage() {
        ModelAndView modelAndView = new ModelAndView("socket");
        return modelAndView;
    }
}
