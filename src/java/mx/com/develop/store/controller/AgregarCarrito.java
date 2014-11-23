package mx.com.develop.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import mx.com.develop.store.model.Producto;
import mx.com.develop.store.model.Venta;

@WebServlet(name = "AgregarCarrito", urlPatterns = {"/ventas/agregar_carrito.do"})
public class AgregarCarrito extends HttpServlet {
    
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction ut;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(false);
            ServletContext context =  getServletContext();
            if(session != null){
                System.out.println("Creation Time = "+ new Date(session.getCreationTime()));
                System.out.println("Last Accessed Time = "+ new Date(session.getLastAccessedTime()));
                Integer idProducto = Integer.parseInt(request.getParameter("id"));
                //List<Producto> productos = (List<Producto>)context.getAttribute("productos");
                List<Producto> productos = (List<Producto>)em.createQuery("select p from Producto p").getResultList();
                synchronized(context){
                    for (Producto producto : productos) {
                        if(producto.getId().equals(idProducto)){
                            Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
                            Integer disponible = producto.getDisponibles() - cantidad;
                            if (disponible >= 0 ) {
                                producto.setDisponibles(disponible);
                                try {
                                    ut.begin();                                
                                    em.merge(producto);
                                    ut.commit();
                                } catch (NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SystemException ex) {
                                    Logger.getLogger(RegistroProducto.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                Venta venta = (Venta)session.getAttribute("venta");
                                if (venta == null) {
                                    venta = new Venta();
                                    session.setAttribute("venta", venta);
                                }
                                Integer seleccionado =  venta.getProductos().get(producto) ;
                                if (seleccionado == null) {
                                    venta.getProductos().put(producto, cantidad);
                                } else {
                                    venta.getProductos().put(producto, seleccionado +cantidad ) ;
                                }
                                request.getRequestDispatcher("lista_carrito.jsp").forward(request, response);
                                break;
                            }else{
                                response.sendRedirect("agregar_carrito_error.jsP");
                            }
                        }
                        
                    }
                }
            }else{
                response.sendRedirect("detalles_productos_error.jsp");
            }
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
