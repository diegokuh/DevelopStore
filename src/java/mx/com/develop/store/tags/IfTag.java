/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.develop.store.tags;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Curso
 */
public class IfTag extends SimpleTagSupport {
    private Boolean condicion;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        try {
            getJspBody().invoke(null);
        } catch (IOException ex) {
            Logger.getLogger(IfTag.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setCondicion(Boolean condicion) {
        this.condicion = condicion;
    }

    public Boolean isCondicion() {
        return condicion;
    }
    
    
}
