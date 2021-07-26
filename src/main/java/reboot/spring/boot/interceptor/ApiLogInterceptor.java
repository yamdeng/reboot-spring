package reboot.spring.boot.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class ApiLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        log.info("ApiLogInterceptor preHandle : " + handler.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        log.info("ApiLogInterceptor postHandle : " + handler.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
        log.info("ApiLogInterceptor afterCompletion : " + handler.toString());
        if(ex != null) {
            log.info("ApiLogInterceptor afterCompletion exception : " + ex.getMessage());
        }
    }
}