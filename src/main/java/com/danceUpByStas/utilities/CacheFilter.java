package com.danceUpByStas.utilities;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This is the CacheFilter class designed to clear cache of web application.
 * @author srevin
 */
public class CacheFilter implements javax.servlet.Filter {

    private FilterConfig filterConfiguration;


    /**
     * This method clears cache of the web application.
     * @param request The servlet request.
     * @param response The servlet response.
     * @param chain The filter chain.
     * @throws java.io.IOException The input/output exception.
     * @throws javax.servlet.ServletException The servlet exception.
     */
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         FilterChain chain)
            throws java.io.IOException, javax.servlet.ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
        httpResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setHeader("Expires", "0");

        chain.doFilter(request, response);

    }

    /**
     * This method initializes the filter configuration.
     * @param filterConfiguration
     */
    public void init(final FilterConfig filterConfiguration) {

        this.filterConfiguration = filterConfiguration;
    }

    /**
     * This method destroys the filter.
     */
    public void destroy() {
    }

}