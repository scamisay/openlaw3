package ar.org.openlaw.controller;

import ar.org.openlaw.domain.law.Law;
import ar.org.openlaw.service.LawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by scamisay on 05/02/16.
 */
@Controller
@RequestMapping(value = "/law")
public class LawController {

    @Autowired
    private LawService lawService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("law/create");
        return mav;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("list", lawService.findAll());
        mav.setViewName("law/list");
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(name = "id") Law law) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("law", law);
        mav.setViewName("law/edit");
        return mav;
    }
}
