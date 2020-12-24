package bank.transaction.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MySpringMvcDispatcherSerlvetIntitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    public MySpringMvcDispatcherSerlvetIntitializer() {
    }

    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    @Override
    public void onStartup(ServletContext aServletContext) throws SecurityException, ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);

    }

    private void registerHiddenFieldFilter(ServletContext aContext){
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null,true,"/*");
    }
}
