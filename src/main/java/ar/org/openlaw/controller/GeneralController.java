package ar.org.openlaw.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by scamisay on 28/10/16.
 */
@Controller
public class GeneralController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/")
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
