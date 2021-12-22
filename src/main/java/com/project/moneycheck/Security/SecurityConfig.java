package com.project.moneycheck.security;

import com.project.moneycheck.security.handler.AuthFailHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
				 .antMatchers("/user/**").hasRole("USER")
//				 .antMatchers("/host/**").hasRole("HOST")
//				 .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
            .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginNormal")
//                    .successHandler(authSuccessHandler)
                    .failureHandler(authFailHandler)
            .and()
                .exceptionHandling().accessDeniedPage("/error")
            .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/main")
                .failureUrl("/login")
                .userInfoEndpoint().userService(principalOAuth2UserService);
    }


}
