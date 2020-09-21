package jm.stockx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private final UserDetailsService userDetailsService;
    private final SecurityUtils securityUtils;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService, SecurityUtils securityUtils) {
        this.userDetailsService = userDetailsService;
        this.securityUtils = securityUtils;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/robots.txt",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline-page.html",
                "/frontend/**",
                "/webjars/**",
                "/frontend-es5/**", "/frontend-es6/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .permitAll();

        http
                .authorizeRequests()
                .antMatchers("/", "/rest/api/**", "/registration/**", "/authorization/**", "/password-recovery/**", "/brand/all", "/news", "/how-it-works").permitAll()
                .antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/admin/**").hasAuthority("ADMIN");

        http.csrf().disable()
                .requestCache().requestCache(new CustomRequestCache(securityUtils))
                .and().authorizeRequests()
                //.requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                .requestMatchers(securityUtils::isFrameworkInternalRequest).permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .failureUrl("/login?error")
                .and().logout().logoutSuccessUrl("/login");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}

