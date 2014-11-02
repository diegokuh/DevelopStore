/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.develop.store.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.develop.store.model.Cliente;

/**
 *
 * @author Cursos Montoya
 */
@WebServlet(name = "Login", urlPatterns = {"/login.do"},
        initParams = {
            @WebInitParam(name = "usuario",value = "diegokuh"),
            @WebInitParam(name = "contrasenia",value = "321")
        }
)
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ServletConfig sc = getServletConfig();
        
        String usuario = sc.getInitParameter("usuario");
        String contrasenia = sc.getInitParameter("contrasenia");
        
        String usrParam = request.getParameter("usuario");
        String passParam = request.getParameter("contrasenia");
        
        /**/
        Cliente clienteEncontrado = null; 
        ServletContext context=getServletContext();
        List<Cliente> clientes=(List<Cliente>) context.getAttribute("clientes");
        System.out.println("Login Clientes " + ((List<Cliente>)context.getAttribute("clientes")).size());
        int i = 0;
        if(clientes == null){
            response.sendRedirect("login_error.jsp");
        } else{
            for(Cliente cliente: clientes){
                System.out.println(cliente.getUsuario());
                if (cliente.getUsuario().equals(usrParam) && cliente.getContrasenia().equals(passParam)) {
                    //context.setAttribute("us", usuarioParametro);
                    clienteEncontrado = cliente;
                    System.out.println("igual");
                    i = 1;
                }
            }            
        }        
           if(i == 1 ){
               //Creamos la sexsion 
               HttpSession session = request.getSession();
                session.setAttribute("cliente", clienteEncontrado);
                session.setMaxInactiveInterval(15*60);//dest5ruye la sesion en segundos
                
                //Cookies
                Cookie cookie = new Cookie("JSESSIONID", session.getId());
                cookie.setMaxAge(15 * 60);
                response.addCookie(cookie);
                
                //response.sendRedirect("lista_productos.view");                
                //enviamos la sesion en la url
                response.sendRedirect(response.encodeRedirectURL("lista_productos.view"));
            }else{
                response.sendRedirect("login_error.jsp");
            }
        
//        if(usrParam.equals(usuario) && passParam.equals(contrasenia)){
//            response.sendRedirect("lista_productos.view");
//        }else{
//            response.sendRedirect("login_error.jsp");
//        }            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
