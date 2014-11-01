
package mx.com.develop.store.listener;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import mx.com.develop.store.model.Cliente;
import mx.com.develop.store.model.Color;
import mx.com.develop.store.model.Producto;
import mx.com.develop.store.model.Talla;
import mx.com.develop.store.model.TipoProducto;

@WebListener()
public class StoreContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
            
        List<Producto> productos = new LinkedList<>();
        productos.add(new Producto(1, Color.ROJO, 167.50, Talla.CHICA, "Playera con estampado", TipoProducto.PLAYERA,5));
        productos.add(new Producto(2, Color.AZUL, 199.99, Talla.GRANDE, "Pantalon de mezclilla", TipoProducto.PANTALON,4));
        productos.add(new Producto(3, Color.MORADO, 257.99, Talla.MEDIANA, "Blusa morada con cuello V.", TipoProducto.BLUSA,7));
        productos.add(new Producto(4, Color.CAFE, 174.5, Talla.GRANDE, "Camisa con boton premium.", TipoProducto.CAMISA,3));
        productos.add(new Producto(5, Color.NEGRO, 480.99, Talla.EXTRA_GRANDE, "Playera para caballero con estampado de Dinosaurio", TipoProducto.PLAYERA,2));
        productos.add(new Producto(6, Color.CAFE, 174.5, Talla.CHICA, "Chamarra", TipoProducto.CAMISA,1));

        context.setAttribute("productos", productos);
        List<Cliente> clientes= new LinkedList<>();
        
        clientes.add(new Cliente("rodo", 26, "dfg", "55", "rodo", "123"));
        context.setAttribute("clientes", clientes);
        
        //Creamos un sevlet dinamico
        ServletRegistration.Dynamic dynamicServlet =  
                context.addServlet("RegistroCliente", "mx.com.develop.store.controller.RegistroCliente");
        //Creamos la url ala cual va a responder 
        dynamicServlet.addMapping("/registro.do");
        List<String> mappings = (List<String>)dynamicServlet.getMappings();
        for (String mapping : mappings) {
            System.out.println("Mapping" + mapping);            
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
