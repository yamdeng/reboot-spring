package reboot.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reboot.spring.bean.vo.LoginInfo;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;
import java.util.UUID;

/*


    1.httpSession
    2.cookie value
    3.json token

 */

@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "loginId") String loginId,
                        @RequestParam(value = "saved", required = false, defaultValue = "false") boolean saved,
                        HttpSession httpSession,
                        HttpServletResponse httpResponse,
                        Model model
    ) {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginId(loginId);
        loginInfo.setName(loginId);
        loginInfo.setLoginToken(UUID.randomUUID().toString());
        httpSession.setAttribute("loginInfo", loginInfo);
        Cookie rememberCookie =
                new Cookie("app-loginId", loginId);
        rememberCookie.setPath("/");

        if (saved) {
            // 24시간
            httpSession.setMaxInactiveInterval(60 * 60 * 24);
            rememberCookie.setMaxAge(60 * 60 * 24 * 30);
        } else {
            httpSession.setMaxInactiveInterval(30);
            rememberCookie.setMaxAge(0);
        }
        model.addAttribute("loginInfo", loginInfo);
        httpResponse.addCookie(rememberCookie);
        return "login-success";
    }

    @GetMapping("/loginInfo")
    public String login(
            @CookieValue(value="app-loginId", required = false) Cookie cookie,
            HttpSession httpSession,
            Model model
    ) {
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        model.addAttribute("loginInfo", loginInfo);
        model.addAttribute("loginId", cookie.getValue() + "@CookieValue");
        return "login-info";
    }

    @GetMapping("/loginFail")
    public String loginFail() {
        return "login-fail";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "login-info";
    }

}
