package mx.com.develop.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.develop.store.model.Producto;
import mx.com.develop.store.model.Venta;

@WebServlet(name = "AgregarCarrito", urlPatterns = {"/ventas/agregar_carrito.do"})
public class AgregarCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session= request.getSession();
        ServletContext context=getServletContext();
        
        System.out.println("Creation time: " + new Date(session.getCreationTime()));
        System.out.println("Last Accessed Time: "+new Date(session.getLastAccessedTime()));
        
        if(session != null){
            Integer idProducto=Integer.parseInt(request.getParameter("id"));
            System.out.println("IdProd" + idProducto);
            synchronized(context){ //bloqueo el contexto para actualizarlo
            List<Producto> productos=(List<Producto>) context.getAttribute("productos");
            for (Producto producto : productos) {
                if(producto.getId().equals(idProducto)){
                    Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
                    Integer disponibles = producto.getDisponibles() - cantidad; 
                    if(disponibles >= 0){
                        producto.setDisponibles(disponibles);
                        Venta venta =(Venta)session.getAttribute("venta");
                        if(venta == null){
                            venta = new Venta();
                            session.setAttribute("venta", venta);
                        }
                        Integer seleccionados = venta.getProductos().get(producto);
                        if (seleccionados==null) {
                            venta.getProductos().put(producto, cantidad);
                        } else {
                             venta.getProductos().put(producto, seleccionados+cantidad);
                        }
                        request.getRequestDispatcher("lista_carrito.jsp").forward(request, response);
                        System.out.println("seleccionamos" + seleccionados);                                                 
                    }else{
                        response.sendRedirect("agregar_carrito_error.jsp");
                    } 
                    System.out.println("Disponibles " + disponibles);
                }
            }            
            System.out.println("productos " + productos.size());
            }
           }else{
            response.sendRedirect("detalles_producto_error.jsp");
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgregarCarrito</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarCarrito at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
