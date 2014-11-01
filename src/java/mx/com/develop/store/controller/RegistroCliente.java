/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.develop.store.controller;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.develop.store.model.Cliente;
import nl.captcha.Captcha;

/**
 *
 * @author Humberto
 */
@WebServlet(name = "RegistroCliente", urlPatterns = {"/Registro.do"})
public class RegistroCliente extends HttpServlet {
    
    public RegistroCliente(){
        System.out.println("Dentro del constructor....");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("en método doPost(HttpServletRequest req, HttpServletResponse resp)");
        resp.setContentType("text/html");
        
        ServletContext ctx = getServletContext();
        //Otros métodos para obtener a ServletContext:
        //ServletContext ctx = req.getServletContext();
        //ServletContext ctx = getServletConfig().getServletContext();
        
        String nombre = req.getParameter("nombre");
        String edad = req.getParameter("edad");
        int intEdad = Integer.parseInt(edad);
        String direccion = req.getParameter("direccion");
        String telefono = req.getParameter("telefono");
        String usuario = req.getParameter("usuario");
        String contrasenia = req.getParameter("contrasenia");
        String answer = req.getParameter("answer");
        boolean captchaCorrecta = false;
//        Enumeration<String> captchas = ctx.getInitParameterNames();
//        while (captchas.hasMoreElements()) {
//            if(ctx.getInitParameter(captchas.nextElement()).equals(captcha)){
//                captchaCorrecta = true;
//                break;
//            }
//        }
        
        Captcha captcha = (Captcha)req.getSession().getAttribute(Captcha.NAME);
        req.setCharacterEncoding("UTF-8");
        captchaCorrecta = captcha.isCorrect(answer);
        if(captchaCorrecta){
            Cliente cliente = new Cliente(nombre, intEdad, direccion, telefono, usuario, contrasenia);

            req.setAttribute("cliente", cliente);
            
            RequestDispatcher rd = req.getRequestDispatcher("registro_cliente_success.jsp");
            rd.forward(req, resp);

            System.out.println("Se ejecutó doPost(HttpServletRequest req, HttpServletResponse resp)");
        }else{
            resp.sendRedirect("captcha_error.jsp");
        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("En método init(ServletConfig config)");
        super.init(config); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() throws ServletException {
        System.out.println("En método init()");
        super.init(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("En método service(ServletRequest req, ServletResponse res)");
        super.service(req, res); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("En método service(HttpServletRequest req, HttpServletResponse resp)");
        
        String requestURI = req.getRequestURI();
        StringBuffer requestURL = req.getRequestURL();
        String servletPath = req.getServletPath();
                
        System.out.println("requestURI: "+requestURI);
        System.out.println("requestURL: "+requestURL);
        System.out.println("servletPath: "+servletPath);
        
        super.service(req, resp); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        System.out.println("En método destroy()");
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
