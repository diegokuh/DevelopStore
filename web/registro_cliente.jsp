<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Develop Store: Registro</title>
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
        
        <form action="registro.do" method="POST">
            <table border="0">
                <tr>
                    <td>Nombre: </td>
                    <td><input type="text" name="nombre" value="" size="20" /></td>
                </tr>
                <tr>
                    <td>Edad: </td>
                    <td><input type="text" name="edad" value="" size="3" /></td>
                </tr>
                <tr>
                    <td>Dirección: </td>
                    <td><input type="text" name="direccion" value="" size="40" /></td>
                </tr>
                <tr>
                    <td>Teléfono: </td>
                    <td><input type="text" name="telefono" value="" size="15" /></td>
                </tr>
                <tr>
                    <td>Usuario: </td>
                    <td><input type="text" name="usuario" value="" size="20" /></td>
                </tr>
                <tr>
                    <td>Contraseña: </td>
                    <td><input type="password" name="contrasenia" value="" size="20" /></td>
                </tr>
                <tr>
                    <td><img src="simpleImg"/></td>
                    <td>Escribe el texto de la imágen:<br /><input type="text" name="answer" /></td>
                </tr>
            </table>
            <br>
            <input type="submit" value="Registrarse" name="submit" />
        </form>
    </body>
</html>

