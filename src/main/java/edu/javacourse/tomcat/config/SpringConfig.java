package edu.javacourse.tomcat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@ComponentScan("edu.javacourse.tomcat")
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
@EnableWebMvc
@EnableJpaRepositories("edu.javacourse.tomcat.repo")


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
        source.setDriverClassName(Objects.requireNonNull(environment.getProperty("hibernate.driver_class")));
        source.setUrl(environment.getProperty("hibernate.connection.url"));
        source.setUsername(environment.getProperty("hibernate.connection.username"));
        source.setPassword(environment.getProperty("hibernate.connection.password"));
        return source;
    }
//    @Bean
//    public JdbcTemplate getJdbcTemplate(){
//        return new JdbcTemplate(getDataSource());
//    }
    private Properties hibernateProperties(){
        Properties properties=new Properties();
        properties.put("hibernate.dialect",environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",environment.getProperty("hibernate.show_sql"));
        return properties;
    }
//    @Bean
//    public LocalSessionFactoryBean sessionFactory(){
//        LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(getDataSource());
//        sessionFactory.setPackagesToScan("edu.javacourse.tomcat.business");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityFactory(){
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean=new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPackagesToScan("edu.javacourse.tomcat.business");
        return entityManagerFactoryBean;
    }
    @Bean
    public PlatformTransactionManager hibernateTransactionManager(){
//        HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager();
//        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityFactory().getObject());
        return transactionManager;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        ThymeleafViewResolver resolver=new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine() );
        registry.viewResolver(resolver);

    }

}
