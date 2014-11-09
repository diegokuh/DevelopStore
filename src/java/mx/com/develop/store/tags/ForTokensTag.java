/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.develop.store.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 *
 * @author Curso
 */
public class ForTokensTag extends BodyTagSupport {
    private String arreglo;
    private String varName;
    private String delimitador;
    String[] tokens;
    private int i = 0;

    @Override
    public int doStartTag() throws JspException {
        
        if(delimitador==null || delimitador.equals("")){
            delimitador = ",";
        }
        
        tokens = arreglo.split(delimitador);
        
        return EVAL_BODY_BUFFERED;
        //return SKIP_BODY;
    }

    @Override
    public void doInitBody() throws JspException {
        pageContext.setAttribute(varName, tokens[i++], PageContext.PAGE_SCOPE);
    }
    
    @Override
    public int doAfterBody() throws JspException {
        if(i<=tokens.length){
            return EVAL_BODY_AGAIN;
        }else{
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        //return EVAL_PAGE;
        return SKIP_PAGE;
    }
    
    public void setArreglo(String arreglo) {
        this.arreglo = arreglo;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public void setDelimitador(String delimitador) {
        this.delimitador = delimitador;
    }
    
}
