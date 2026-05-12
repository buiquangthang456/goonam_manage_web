package com.goonamvina.goonamvina.config;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bean mã hóa mật khẩu sử dụng BCrypt.
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf
                        // Bỏ qua CSRF cho các URL cụ thể
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/contact", "POST"),
                                new AntPathRequestMatcher("/admin/reply/moveToTrash/**", "POST"),
                                new AntPathRequestMatcher("/admin/trash/restore/**", "POST"),
                                new AntPathRequestMatcher("/admin/trash/delete/**", "DELETE")
                        )
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "admin")
                        .anyRequest().permitAll()
                )

                .formLogin(login -> login
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login") // URL xử lý đăng nhập.
                        .successHandler(successHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                        .logoutSuccessUrl("/admin/login")
                        .permitAll()
                )
                // ✅ Bỏ hẳn trang error.html, show lỗi thẳng ra response
                .exceptionHandling(ex -> ex
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("text/plain;charset=UTF-8");
                            response.getWriter().write("Access Denied: " + accessDeniedException.getMessage());
                        })
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("text/plain;charset=UTF-8");
                            response.getWriter().write("Unauthorized: " + authException.getMessage());
                        })
                );
        ;


        return http.build();
    }
}
