//package com.learn.bulletin;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests().antMatchers("/api/*").access("#oauth2.hasScope('openid')");
//        http.authorizeRequests().antMatchers("/api/*").hasRole("read");
//
//    }
//
//
//
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/**").access("hasRole('ROLE_USER')")
////                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
////                .anyRequest().authenticated().and()
////                .rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7).key("1").and()
////                .formLogin().loginPage("/login").failureUrl("/login?error").permitAll().and()
////                .logout().permitAll();
////    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(customUserService());
////    }
//}
