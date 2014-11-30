/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.develop.store.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.com.develop.store.model.Color;
import mx.com.develop.store.model.Producto;
import mx.com.develop.store.model.Talla;
import mx.com.develop.store.model.TipoProducto;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.Part;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


/**
 * 
 * @author Humberto BanueloS
 */
@WebServlet(name = "RegistroProducto", urlPatterns = { "/admin/registro_producto.do" })
@MultipartConfig
@DeclareRoles("Administrator")
@ServletSecurity(httpMethodConstraints = {
        @HttpMethodConstraint(value="POST",
                rolesAllowed="Administrator",
                emptyRoleSemantic = ServletSecurity.EmptyRoleSemantic.PERMIT)
    }
)
public class RegistroProducto extends HttpServlet {
    @PersistenceContext
    private EntityManager em;
    @Resource
    private UserTransaction utx;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * 
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <String> listaErrores = new ArrayList <String>();
                List <Producto> productos = null;
		Color color = null;
		double precio = 0.0;
		Talla talla = null;
		String descripcion = null;
		TipoProducto tipo = null;
                int disponibles=0;
                disponibles=Integer.parseInt(request.getParameter("disponibles"));
		try
		{
			color = Color.valueOf(request.getParameter("color"));
		} catch (IllegalArgumentException e)
		{
			listaErrores.add("Debes seleccionar el 'Color' del producto, es un campo obligatorio.");
		}

		try
		{
			precio = new Double(request.getParameter("precio"));
		} catch (NumberFormatException e)
		{
			listaErrores.add("El precio debe ser un n�mero decimal.");
		}
		try
		{
			talla = Talla.valueOf(request.getParameter("talla"));
		} catch (IllegalArgumentException e)
		{
			listaErrores.add("Debes seleccionar la 'Talla' del producto, es un campo obligatorio.");
		}
		descripcion = request.getParameter("descripcion");
		try
		{
			tipo = TipoProducto.valueOf(request.getParameter("tipo"));
		} catch (IllegalArgumentException e)
		{
			listaErrores.add("Debes seleccionar el 'Tipo' del producto, es un campo obligatorio.");
		}

		if (listaErrores.isEmpty())
		{
                    // Obtener acceso al archivo del cliente
                    Part p1 = request.getPart("imagen");
                    InputStream is = p1.getInputStream();

                    // Obtener el nombre del archivo de lado del servidor.
                    String outputImagen = this.getServletContext().getRealPath("admin/temp.png");
                    FileOutputStream os = new FileOutputStream(outputImagen);
                    // Escribir los bytes del lado del servidor.
                    int ch = is.read();
                    while (ch != -1) {
                    os.write(ch);
                    ch = is.read();
                    }
                    os.close();
                    
                        productos = (List <Producto>)getServletContext().getAttribute("productos");
                        if(productos==null){
                            productos = new ArrayList<Producto>();
                        }
			Producto producto = new Producto(color, precio, talla, descripcion, tipo, disponibles);
                    try {
                        utx.begin();
                        em.persist(producto);
                        utx.commit();
                    } catch (Exception ex) {
                        Logger.getLogger(RegistroProducto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        request.setAttribute("producto", producto);
                        productos.add(producto);
                        getServletContext().setAttribute("productos", productos);
			RequestDispatcher rd = request.getRequestDispatcher("registro_producto_success.jsp");
			rd.forward(request, response);
		} else
		{
			request.setAttribute("listaErrores", listaErrores);
			RequestDispatcher rd = request.getRequestDispatcher("registro_producto_error.jsp");
			rd.forward(request, response);
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
