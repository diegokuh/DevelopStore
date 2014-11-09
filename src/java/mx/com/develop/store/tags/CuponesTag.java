/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.develop.store.tags;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.DynamicAttributes;

/**
 *
 * @author Curso
 */
public class CuponesTag extends BodyTagSupport implements DynamicAttributes{

    Map<String, Object> map = new HashMap<>();
    
    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        map.put(localName, value);
    }

    @Override
    public int doStartTag() throws JspException {
        Collection<Object> values = map.values();
        JspWriter out = pageContext.getOut();
        for (Object object : values) {
            try {
                out.println("<li>"+object+"</li>");
            } catch (IOException ex) {
                Logger.getLogger(CuponesTag.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return super.doStartTag();
    }

    
    
}
