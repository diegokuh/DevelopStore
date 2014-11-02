<%-- 
    Document   : registro_cliente_success
    Created on : Oct 11, 2014, 6:21:49 PM
    Author     : Curso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <table border='0' cellpadding='5' cellspacing='0' width='800'> 
        <tr bgcolor='#3882C7' align='center' valign='center' height='20'> 
            <td>
                <h3><font color="white">Develop Store: Registro de clientes.</h3>
            </td> 
        </tr> 
        <tr align='right'> 
            <td>
                <b>Usted está aquí:</b> <a href="index.jsp">Inicio</a>/Registro
            </td> 
        </tr> 
    </table>
    
    <h3>Te has registrado de manera satisfactoria bajo la siguiente información:</h3>
    
    <p>
        <b>Nombre:</b> ${cliente.nombre}<br />
        <b>Edad:</b> ${cliente.edad}<br />
        <b>Dirección:</b> ${cliente.direccion}<br />
        <b>Teléfono:</b> ${cliente.telefono}<br />
        <b>Usuario:</b> ${cliente.usuario}
    </p>
    </body>
</html>
