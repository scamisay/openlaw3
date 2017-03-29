package ar.org.openlaw.interceptor;

import ar.org.openlaw.service.impl.CurrentUserDetailsService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by scamisay on 01/11/16.
 */
public class LoggedInUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        try{
            CurrentUserDetailsService.CurrentUser currentUser = CurrentUserDetailsService.getCurrentUser(httpServletRequest.getSession());
            modelAndView.addObject("user", currentUser.getUser().getUsername());
        }catch (Exception e){}
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
