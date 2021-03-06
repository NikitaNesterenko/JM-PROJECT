//package jm.stockx.config;
///**
// * I18n with @User.localeTag
// */
//
//import jm.stockx.UserService;
//import jm.stockx.dto.UserDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.CookieLocaleResolver;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//
//@ComponentScan("jm.stockx")
//@Configuration
//public class LocaleResolverConfig implements WebMvcConfigurer {
//
//    private UserDto userDto;
//
//    @Autowired
//    public LocaleResolverConfig(UserDto userDto) {
//        this.userDto = userDto;
//    }
//
//    @Bean
//    public LocaleResolver localeResolver() {
////        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
////        sessionLocaleResolver.setDefaultLocale(Locale.US);
////        return sessionLocaleResolver;
//        return new CookieLocaleResolver();
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeInterceptor() {
//        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
//        localeInterceptor.setParamName(userDto.getLocaleTag());
//        return localeInterceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeInterceptor());
//    }
//
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource
//                = new ReloadableResourceBundleMessageSource();
//
//        messageSource.setBasename("classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//
//    @Bean
//    public LocalValidatorFactoryBean getValidator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(messageSource());
//        return bean;
//    }
//
//
//}
