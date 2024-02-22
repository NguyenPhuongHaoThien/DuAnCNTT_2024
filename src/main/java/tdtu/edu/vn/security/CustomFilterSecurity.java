package tdtu.edu.vn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class CustomFilterSecurity {
    @Autowired
    CustomFilterJwt customFilterJwt;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable() // chống tấn công bằng việc copy token
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)// Không sử dụng session
                .and()
                .authorizeHttpRequests() // Bắt đầu cấu hình cho request
                .requestMatchers("/login", "/register").permitAll()
                .anyRequest().authenticated() // Tất cả các request khác đều cần xác thực
                .and()
                // Disable form login
                .httpBasic(); // Đăng nhập qua url /login

        http.addFilterBefore(customFilterJwt, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
