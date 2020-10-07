//package jm.stockx;
//
//import jm.stockx.handlers.LoginSuccessHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
//
//    private final UserDetailsService userDetailsService;
//    private final SecurityUtils securityUtils;
//    private final LoginSuccessHandler successHandler;
//
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    public SecurityConfig(UserDetailsService userDetailsService, SecurityUtils securityUtils, LoginSuccessHandler successHandler) {
//        this.userDetailsService = userDetailsService;
//        this.securityUtils = securityUtils;
//        this.successHandler = successHandler;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(
//                "/VAADIN/**",
//                "/favicon.ico",
//                "/robots.txt",
//                "/manifest.webmanifest",
//                "/sw.js",
//                "/offline-page.html",
//                "/frontend/**",
//                "/webjars/**",
//                "/frontend-es5/**", "/frontend-es6/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers(securityUtils::isFrameworkInternalRequest).permitAll()
//                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                .antMatchers("/admin/**", "/", "/rest/api/**", "/registration/**",
//                        "/authorization/**", "/password-recovery/**", "/brand/all", "/news",
//                        "/how-it-works", "/test-template", "/item/img/upload", "/item/img/download",
//                        "/itemblock", "/brand").hasRole("ADMIN");
//
//        http.csrf().disable()
//                .requestCache().requestCache(new CustomRequestCache(securityUtils))
//                .and().formLogin()
//                .loginPage("/login").permitAll()
//                .loginProcessingUrl("/login")
//                .successHandler(successHandler)
//                .failureUrl("/login?error")
//                .and().logout().logoutSuccessUrl("/login");
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//}
//
