<%-- 
    Document   : lista_productos
    Created on : Oct 4, 2014, 8:07:42 PM
    Author     : Humberto
--%>

<%@page import="mx.com.develop.store.model.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    List<Producto> lista = (List<Producto>)request.getAttribute("listaProductos");
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
        <title>Develop Store: Listado de Productos</title>
        <style type='text/css'>
            #table { 
                border-collapse: collapse; 
            }
        </style>
    </head>
    <body>
        <table border='0' cellpadding='5' cellspacing='0' width='800'> 
            <tr bgcolor='#3882C7' align='center' valign='center' height='20'> 
                <td>
                    <h3><font color='white'>Develop Store: Listado de Productos</h3>
                </td> 
            </tr> 
            <tr align='right'> 
                <td>
                    <table>
                        <tr>

                            <td>Usuario: </td>
                            <td> ${usuario} </td>
                        </tr>
                    </table>
                </td> 
            </tr> 
        </table>
        <b>Usted está aquí:</b> <a href="index.html">Inicio</a>/Listado de Productos        
        <h2>Lista de Productos:</h2>
        <table border='1' width='800' id='table'>
            <thead>
                <tr bgcolor='#3882C7'>
                    <th>No.</th>
                    <th>Descripción</th>
                    <th>Tipo</th>
                    <th>Color</th>
                    <th>Talla</th>
                    <th>Precio</th>
                    <th>Disponibles</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <fmt:setLocale value="es_MX" />
                <c:forEach items="${listaProductos}" var="lista" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${lista.descripcion}</td>
                    <td>${lista.tipo.titulo}</td>
                    <td>${lista.color.titulo}</td>
                    <td>${lista.talla}</td>
                    <td><fmt:formatNumber type="currency" value="${lista.precio}" /></td>
                    <td></td>
                    <td><img src="imagenes/carrito.png" width="40" height="40" alt="carrito"/>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
