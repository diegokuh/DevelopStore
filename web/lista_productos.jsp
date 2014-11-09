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
<%@taglib uri="/store.develop.com.mx" prefix="d" %>
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
        <c:import url="header.jsp">
            <c:param name="titulo" value="Listado de Productos" />
        </c:import>
        <%--<c:redirect url="login.html" />--%>
        <b>Usted está aquí:</b> <a href="index.jsp">Inicio</a>/Listado de Productos        
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
                <%--<c:forEach items="${productos}" var="lista" varStatus="status" >--%>
                <d:myForEach lista="${productos}" varName="lista" indice="index">
                <tr>
                    <%--<td>${status.count}</td>--%>
                    <td>${index}</td>
                    <td>${lista.descripcion}</td>
                    <td>${lista.tipo.titulo}</td>
                    <td>${lista.color.titulo}</td>
                    <td>${lista.talla}</td>
                    <td><fmt:formatNumber type="currency" value="${lista.precio}" /></td>
                    <td>${lista.disponibles}</td>
                    <td>
                        <a href="ventas/detalles_producto.view?id=${lista.id}"><img src="imagenes/carrito.png" width="40" height="40" alt="carrito"/></a>
                    </td>
                </tr>
                </d:myForEach>
                <%--</c:forEach>--%>
            </tbody>
        </table>
    </body>
</html>
