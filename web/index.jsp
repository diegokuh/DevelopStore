<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Develop Store: Inicio</title>
    </head>
    <body>
        <table border='0' cellpadding='5' cellspacing='0' width='800'> 
            <tr bgcolor='#3882C7' align='center' valign='center' height='20'> 
                <td>
                    <h3><font color="white">Develop Store: Inicio</h3>
                </td> 
            </tr> 
            <tr align='right'> 
                <td>
                    <table>
                        <tr>
                            <td><a href="login.html">Login</a></td>
                            <td><a href="registro_cliente.jsp">Regístrate</a></td>
                        </tr>
                    </table>
                </td> 
            </tr> 
        </table>
        <h2>Bienvenido a DEVELOP Store.</h2>
        <h3>Clientes</h3>
        <ul>
            <li><a href="lista_productos.view">Ver listado de Productos.</a></li>
        </ul>
        <%!
            int i = 0;
        %>
        No. de visitas: <%= i++ %>
    </body>
</html>
