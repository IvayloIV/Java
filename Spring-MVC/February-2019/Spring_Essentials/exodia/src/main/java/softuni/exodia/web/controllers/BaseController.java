package softuni.exodia.web.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    public ModelAndView view(String view, ModelAndView modelAndView) {
        modelAndView.addObject("view", view);
        modelAndView.setViewName("fragments/base-layout");
        return modelAndView;
    }

    public String redirect(String path) {
        return "redirect:" + path;
    }
}
