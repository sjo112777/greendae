package kr.co.greendae.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SecurityConfig {
/*
  *  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF 비활성화 (선택 사항)
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // X-Frame-Options 해제
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()); // 모든 요청 허용 (개발용)

        return http.build();
    }

 */
}
