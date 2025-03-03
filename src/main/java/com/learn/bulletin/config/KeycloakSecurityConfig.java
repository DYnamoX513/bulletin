//package com.learn.bulletin;
//
//import org.keycloak.adapters.KeycloakConfigResolver;
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
//import org.keycloak.adapters.springsecurity.KeycloakSecurityComponents;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.keycloak.adapters.springsecurity.filter.KeycloakAuthenticationProcessingFilter;
//import org.keycloak.adapters.springsecurity.filter.KeycloakPreAuthActionsFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
//import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
//public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
////
////    @Bean
////    public GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
////        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
////        mapper.setConvertToUpperCase(true);
////        return mapper;
////    }
////
////    @Override
////    protected KeycloakAuthenticationProvider keycloakAuthenticationProvider() {
////        final KeycloakAuthenticationProvider provider = super.keycloakAuthenticationProvider();
////        provider.setGrantedAuthoritiesMapper(grantedAuthoritiesMapper());
////        return provider;
////    }
////
////    @Override
////    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(keycloakAuthenticationProvider());
////    }
////
////    @Override
////    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
////        return new NullAuthenticatedSessionStrategy();
////    }
////
////    @Override
////    protected void configure(final HttpSecurity http) throws Exception {
////        super.configure(http);
////        http
////                .authorizeRequests()
////                .antMatchers("/admin").hasRole("ADMIN")
////                .antMatchers("/user").hasRole("USER")
////                .anyRequest().permitAll();
////    }
////
////    @Bean
////    KeycloakConfigResolver keycloakConfigResolver() {
////        return new KeycloakSpringBootConfigResolver();
////    }
////
//
//    @Bean
//    public KeycloakConfigResolver KeycloakConfigResolver() {
//        return new KeycloakSpringBootConfigResolver();
//    }
//
//    @Primary
//    @Bean
//    KeycloakSpringBootConfigResolver keycloakSpringBootConfigResolver(){
//        return new KeycloakSpringBootConfigResolver();
//    }
////
////    @Bean
////    public FilterRegistrationBean keycloakAuthenticationProcessingFilterRegistrationBean(
////            final KeycloakAuthenticationProcessingFilter filter) {
////        final FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
////        registrationBean.setEnabled(false);
////        return registrationBean;
////    }
////
////    @Bean
////    public FilterRegistrationBean keycloakPreAuthActionsFilterRegistrationBean(
////            final KeycloakPreAuthActionsFilter filter) {
////        final FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
////        registrationBean.setEnabled(false);
////        return registrationBean;
////    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
//        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
//        auth.authenticationProvider(keycloakAuthenticationProvider);
//    }
//
//
//
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        super.configure(http);
//        http
//                .authorizeRequests()
//                .antMatchers("/features*").hasRole("user")
//                .anyRequest().permitAll();
//
//    }
//
//}
