package com.apress.timesheets.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ContextFilter implements Filter {
   
   public static final String ATTRIBUTE_CONFIG = "attribute-parameter";
   public static final String DEFAULT_CONTEXT_ATTRIBUTE = "ctx";
   private String ctxParam = DEFAULT_CONTEXT_ATTRIBUTE;

   public void destroy() {}

   public void doFilter(
            final ServletRequest request,
            final ServletResponse response,
            final FilterChain chain) 
      throws IOException, ServletException 
   {
      final HttpServletRequest rq = (HttpServletRequest)request;
      if( rq.getAttribute(ContextFilter.class.getName()) == null) {
         rq.setAttribute(ctxParam, rq.getContextPath());
         rq.setAttribute(ContextFilter.class.getName(), Boolean.TRUE);
      }
      chain.doFilter(request, response);
   }

   public void init(final FilterConfig config) 
      throws ServletException 
   {
      if( config.getInitParameter(ATTRIBUTE_CONFIG) != null ) {
         ctxParam = config.getInitParameter(ATTRIBUTE_CONFIG);
      }
   }
}
