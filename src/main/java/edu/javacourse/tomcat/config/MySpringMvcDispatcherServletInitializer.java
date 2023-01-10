package edu.javacourse.tomcat.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    @Override
    public void onStartup(ServletContext aServletContext)throws ServletException {
         super.onStartup(aServletContext);
         registerHiddenField(aServletContext);
    }
    private void registerHiddenField(ServletContext aContext){
       aContext.addFilter("hiddenHttpMethodFilter",new HiddenHttpMethodFilter()).
               addMappingForUrlPatterns(null,true,"/*");
    }
}
