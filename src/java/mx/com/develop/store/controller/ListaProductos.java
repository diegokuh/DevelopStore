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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.develop.store.model.Color;
import mx.com.develop.store.model.Producto;
import mx.com.develop.store.model.Talla;
import mx.com.develop.store.model.TipoProducto;

/**
 *
 * @author Curso
 */
@WebServlet(name = "ListaProductos", urlPatterns = {"/lista_productos.view"})
public class ListaProductos extends HttpServlet {

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
        List<Producto> productos = new LinkedList<>();
        
        productos.add(new Producto(1, Color.ROJO, 50.00, Talla.MEDIANA, "Camisa Polo", TipoProducto.CAMISA));
        productos.add(new Producto(2, Color.AZUL, 70.00, Talla.GRANDE, "Pantalon mezclilla", TipoProducto.PANTALON));
        productos.add(new Producto(3, Color.NARANJA, 90.00, Talla.EXTRA_GRANDE, "Blusa de verano", TipoProducto.BLUSA));
        productos.add(new Producto(4, Color.VERDE, 45.00, Talla.CHICA, "Playhera con estapado", TipoProducto.PLAYERA));
        productos.add(new Producto(5, Color.NEGRO, 60.00, Talla.MEDIANA, "Camisa de vestir", TipoProducto.CAMISA));
        
        request.setAttribute("listaProductos", productos);
        
        RequestDispatcher rd = request.getRequestDispatcher("lista_productos.jsp");
        rd.forward(request, response);
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
