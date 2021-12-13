package com.project.moneycheck.Security;

import com.project.moneycheck.Security.handler.AuthFailHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalOAuth2UserService principalOAuth2UserService;
    //    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailHandler authFailHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/sessionCheck").authenticated()
                .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/host/**").hasRole("HOST")
//                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/main")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginNormal")
//                    .successHandler(authSuccessHandler)
                .failureHandler(authFailHandler)
//                핸들러로 조정해야 할 지도?
//                .defaultSuccessUrl("/main")
//                .failureUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/error")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .failureUrl("/login")
                .userInfoEndpoint().userService(principalOAuth2UserService);
//        http.exceptionHandling().accessDeniedPage();
    }
}
