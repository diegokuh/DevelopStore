/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.develop.store.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cursos Montoya
 */
/* este se comento para declararlo desde el lisener
    @WebFilter(filterName = "VentasFilter", urlPatterns = {"/ventas/*"}, dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.REQUEST}, initParams = {
    @WebInitParam(name = "Name", value = "Value")})
*/
public class VentasFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter ");
        request.setAttribute("filtro", "Entro al filtro.");
        ServletContext context = request.getServletContext();
        //preprosesamiento}
        HttpSession session = ((HttpServletRequest)request).getSession(false);
        if (session != null) {
            if (session.getAttribute("cliente") != null) {
                chain.doFilter(request, response);
            }else{ 
                ((HttpServletResponse)response).sendRedirect(context.getContextPath() +"/login.html");

            }
        }else{
                ((HttpServletResponse)response).sendRedirect(context.getContextPath() +"/login.html");
        }
        // aqui se ejecuta el jsp
        //posprocesamiento
    }

    @Override
    public void destroy() {
        System.out.println(" destroy");
    }
    
   
   
    
}
