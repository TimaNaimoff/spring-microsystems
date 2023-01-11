package edu.javacourse.tomcat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@ComponentScan("edu.javacourse.tomcat")
@EnableWebMvc
@PropertySource("classpath:database.properties")
public class  SpringConfig implements WebMvcConfigurer {
    private final Environment environment;
    private final ApplicationContext context;
    @Autowired
    public SpringConfig(ApplicationContext context,Environment environment)
    {
        this.environment=environment;
        this.context=context;
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver resolver=new SpringResourceTemplateResolver();
        resolver.setApplicationContext(context);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        return resolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine engine=new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        engine.setEnableSpringELCompiler(true);
        return engine;
    }
    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(Objects.requireNonNull(environment.getProperty("db.driver")));
        source.setUrl(environment.getProperty("db.url"));
        source.setUsername(environment.getProperty("db.login"));
        source.setPassword(environment.getProperty("db.password"));
        return source;
    }
    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDataSource());
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        ThymeleafViewResolver resolver=new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine() );
        registry.viewResolver(resolver);

    }

}
