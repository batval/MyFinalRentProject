package com.batval.myrent.configuration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Application initialization class
 * extends of {@link AbstractAnnotationConfigDispatcherServletInitializer} class
 *
 * @author Baturo Valery
 * @version 1.0
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Get web configuration
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /**
     * Get servlet configuration
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    /**
     * Get servlet mappings. Processing all requests "/".
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    /**
     * Filter for pre-processing requests (for encoding)
     */
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }
}
