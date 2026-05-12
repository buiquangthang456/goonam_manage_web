package com.goonamvina.goonamvina.Controller;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;


import java.util.Locale;

@Controller
public class LanguageController {

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver != null) {
            localeResolver.setLocale(request, response, new Locale(lang));
        }

        // Lưu ngôn ngữ vào cookie để sử dụng cho các lần truy cập sau
        Cookie cookie = new Cookie("lang", lang);
        cookie.setMaxAge(60 * 60 * 24 * 365); // Lưu trong 1 năm
        response.addCookie(cookie);

        // Trả về trang trước đó mà người dùng đã truy cập
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}