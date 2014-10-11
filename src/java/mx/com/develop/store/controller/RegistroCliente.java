/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.develop.store.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Humberto
 */
@WebServlet(name = "RegistroCliente", urlPatterns = {"/registro.do"})
public class RegistroCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>\n" + "    <head>\n" + "        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\n" + "        <title>Develop Store: Registro</title>\n"
                    + "    </head>\n" + "    <body>\n" + "        <table border='0' cellpadding='5' cellspacing='0' width='800'> \n"
                    + "            <tr bgcolor='#3882C7' align='center' valign='center' height='20'> \n" + "                <td>\n"
                    + "                    <h3><font color='white'>Develop Store: Registro de clientes.</h3>\n" + "                </td> \n" + "            </tr> \n"
                    + "            <tr align='right'> \n" + "                <td>\n" + "                    <b>Usted esta aqui:</b> <a href='index.html'>Inicio</a>/Registro \n"
                    + "                </td> \n" + "            </tr> \n" + "        </table>\n" + "        \n" + "        <form action='Registro.do' method='POST'>\n"
                    + "            <table border='0'>\n" + "                <tr>\n" + "                    <td>Nombre: </td>\n"
                    + "                    <td><input type='text' name='nombre' value='' size='20' /></td>\n" + "                </tr>\n" + "                <tr>\n"
                    + "                    <td>Edad: </td>\n" + "                    <td><input type='text' name='edad' value='' size='3' /></td>\n" + "                </tr>\n"
                    + "                <tr>\n" + "                    <td>Direccion: </td>\n" + "                    <td><input type='text' name='direccion' value='' size='40' /></td>\n"
                    + "                </tr>\n" + "                <tr>\n" + "                    <td>Telefono: </td>\n"
                    + "                    <td><input type='text' name='telefono' value='' size='15' /></td>\n" + "                </tr>\n" + "                <tr>\n"
                    + "                    <td>Usuario: </td>\n" + "                    <td><input type='text' name='usuario' value='' size='20' /></td>\n" + "                </tr>\n"
                    + "                <tr>\n" + "                    <td>Contraseña: </td>\n" + "                    <td><input type='password' name='contrasenia' value='' size='20' /></td>\n"
                    + "                </tr>\n");
            int aleatorio = ((int) (Math.random() * 3 + 1));
            out.println("<tr>\n" + "                    <td><img src=\"imagenes/" + aleatorio + ".png\" width=\"137\" height=\"56\" alt=\"1\"/>\n" + "                    </td>\n"
                    + "                    <td>\n" + "                        Escribe el texto de la imagen.<br/>\n"
                    + "                        <input type='text' name='captcha' value='' size='20' />                        \n" + "                    </td>\n" + "                </tr>");
            out.println(" " + "            </table>\n" + "            <br>\n" + "            <input type='submit' value='Registrarse' name='submit' />\n" + "        </form>\n" + "    </body>\n"
                    + "</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }
    
    
}
