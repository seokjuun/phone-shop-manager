package me.hongseokjun.myapp.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import me.hongseokjun.myapp.user.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final String jsonStr = "{\"result\n : \"login\"}";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        if(userDto == null){
            // ajax 요청
            // 클라이언트가 헤더에 "ajax":"true" <= board:jsp
            // => {"result" : "login"} json 문자열 응답
            // 클라이언트의 js 에서 window.location.href 이용해서 페이지 이동
            if("true".equals(request.getHeader("ajax"))){
                // json 문자열 내려줘야 함
                response.getWriter().write(jsonStr);
            } else { // page 요청 => login 페이지로 이동 (redirection)
                response.sendRedirect("/pages/login");
            }
            return false;
        }
        return true;
    }
}
