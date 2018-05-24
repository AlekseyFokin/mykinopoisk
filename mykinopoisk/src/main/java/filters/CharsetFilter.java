/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Фокин
 */
public class CharsetFilter implements Filter
 {
 private String encoding;

 @Override
 public void init(FilterConfig config) throws ServletException
 {
  encoding = config.getInitParameter("requestEncoding");

  if( encoding==null ) encoding="UTF-8";
 }

 @Override
 public void doFilter(ServletRequest request, ServletResponse response, FilterChain       next)
 throws IOException, ServletException
 {
  // Respect the client-specified character encoding
  // (see HTTP specification section 3.4.1)
  if(null == request.getCharacterEncoding())
    request.setCharacterEncoding(encoding);


  /**
* Set the default response content type and encoding
*/
  String uri = ((HttpServletRequest)request).getRequestURI();
  if ( uri.indexOf(".css") > 0){
       next.doFilter(request, response);
    }
response.setContentType("text/html; charset=UTF-8");
response.setCharacterEncoding("UTF-8");


  next.doFilter(request, response);
 }

  public void destroy(){}

    
 }
