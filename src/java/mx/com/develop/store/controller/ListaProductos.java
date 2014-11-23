/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.develop.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.develop.store.listener.StoreAsyncListener;
import mx.com.develop.store.model.Color;
import mx.com.develop.store.model.Producto;
import mx.com.develop.store.model.Talla;
import mx.com.develop.store.model.TipoProducto;

/**
 *
 * @author Curso
 */
@WebServlet(name = "ListaProductos", urlPatterns = {"/lista_productos.view"},asyncSupported = true)
public class ListaProductos extends HttpServlet {
    
    private static Logger log = Logger.getLogger(ListaProductos.class.getName());
    
    @PersistenceContext
    private EntityManager em;
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
        List<Producto> productos = null;
//        productos = new LinkedList<>();
//        
//        productos.add(new Producto(1, Color.ROJO, 50.00, Talla.MEDIANA, "Camisa Polo", TipoProducto.CAMISA));
//        productos.add(new Producto(2, Color.AZUL, 70.00, Talla.GRANDE, "Pantalon mezclilla", TipoProducto.PANTALON));
//        productos.add(new Producto(3, Color.NARANJA, 90.00, Talla.EXTRA_GRANDE, "Blusa de verano", TipoProducto.BLUSA));
//        productos.add(new Producto(4, Color.VERDE, 45.00, Talla.CHICA, "Playhera con estapado", TipoProducto.PLAYERA));
//        productos.add(new Producto(5, Color.NEGRO, 60.00, Talla.MEDIANA, "Camisa de vestir", TipoProducto.CAMISA));
        
        /**
         * productos = (List<Producto>)getServletContext().getAttribute("productos");
         * */
        productos = (List<Producto>) em.createQuery("select p from Producto p").getResultList();
        if(productos==null){
            RequestDispatcher rd = request.getRequestDispatcher("lista_productos_error.jsp");
            rd.forward(request, response);
        }else{
            final AsyncContext ac = request.startAsync();
            ac.addListener(new StoreAsyncListener());
            ac.start(new Runnable() {

                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        log.log(Level.INFO,"Hilo ejecutando tarea asíncrona #"+i);
                        try {
                            Thread.currentThread().sleep(1000);
                            ((HttpServletRequest)ac.getRequest())
                                    .getSession().setAttribute("process", (i+1)*10);
//                            if(i==8){
//                                ac.dispatch("proceso_success.jsp");
//                            }
                        } catch (InterruptedException ex) {
                            log.log(Level.SEVERE, null, ex);
                        }
                    }
                    ac.complete();
                }
            });
            //request.setAttribute("usuario", "Diego Lira");
            request.setAttribute("productos", productos);
            
            RequestDispatcher rd = request.getRequestDispatcher("lista_productos.jsp");
            rd.forward(request, response);
            response.flushBuffer();
        }
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
