/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.develop.store.tags;

import java.text.NumberFormat;
import java.util.Locale;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Curso
 */
public class FormatearMonedaTag extends SimpleTagSupport {
    private Double precio;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("ES", "MX"));
            String precioS = nf.format(precio);
            
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
             out.println(precioS);
        } catch (java.io.IOException ex) {
            throw new JspException("Error in FormatearMonedaTag tag", ex);
        }
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
}
