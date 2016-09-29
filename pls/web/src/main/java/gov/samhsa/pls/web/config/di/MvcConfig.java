package gov.samhsa.pls.web.config.di;

import gov.samhsa.pls.web.controller.ControllerBasePackageMarker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.Optional;

/**
 * WebMvcConfigurer implementation defines callback methods to customize the Java-based configuration for Spring MVC enabled via @EnableWebMvc.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {ControllerBasePackageMarker.class})
public class MvcConfig extends WebMvcConfigurerAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${corsAllowedOrigins}")
    private String corsAllowedOrigins;

    @Value("${corsAllowedMethods}")
    private String corsAllowedMethods;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        CorsRegistration reg = registry.addMapping("/providers/**");

        getCommaSeparatedValuesAsOptionalStringArray(corsAllowedMethods)
                .ifPresent(reg::allowedMethods);
        getCommaSeparatedValuesAsOptionalStringArray(corsAllowedOrigins)
                .ifPresent(reg::allowedOrigins);
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        logger.debug("configureDefaultServletHandling is called");
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.debug("addViewControllers is called");

        registry.addViewController("/").setViewName("index");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        logger.debug("Setting up viewResolver bean");

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    private static Optional<String[]> getCommaSeparatedValuesAsOptionalStringArray(String commaSeparatedValues) {
        return Optional
                .of(Arrays
                        .stream(commaSeparatedValues.split(","))
                        .map(String::trim)
                        .filter(StringUtils::hasText)
                        .toArray(String[]::new))
                .filter(array -> array.length > 0);
    }
}
