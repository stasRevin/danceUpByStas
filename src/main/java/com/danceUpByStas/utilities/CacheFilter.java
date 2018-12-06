package com.danceUpByStas.utilities;

import javax.servlet.*;
import javax.servlet.http.*;

public class CacheFilter implements javax.servlet.Filter {

    private FilterConfig filterConfiguration;


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

    public void init(final FilterConfig filterConfiguration) {

        this.filterConfiguration = filterConfiguration;
    }

    public void destroy() {
    }


}