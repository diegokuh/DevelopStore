/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.develop.store.tags;

import java.util.List;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import mx.com.develop.store.model.Producto;

/**
 *
 * @author Curso
 */
public class ForEachTag extends SimpleTagSupport {
    private List<Producto> lista;
    private String varName;
    private String indice;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspContext ctx = getJspContext();
        
        try {
            
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");
            JspFragment f = getJspBody();
            int i=0;
            for (Producto producto : lista) {
                ctx.setAttribute(varName, producto, PageContext.PAGE_SCOPE);
                ctx.setAttribute(indice, i++, PageContext.PAGE_SCOPE);
                f.invoke(null);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");
        } catch (java.io.IOException ex) {
            throw new JspException("Error in ForEachTag tag", ex);
        }
    }

    public void setLista(List<Producto> lista) {
        this.lista = lista;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public void setIndice(String indice) {
        this.indice = indice;
    }
    
}
