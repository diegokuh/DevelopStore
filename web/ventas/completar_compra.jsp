<%-- 
    Document   : completar_compra
    Created on : Jun 10, 2014, 12:58:26 PM
    Author     : Humberto
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="/store.develop.com.mx" prefix="d" %>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="mx.com.develop.store.model.Producto"%>
<%@page import="mx.com.develop.store.model.Producto"%>
<%@page import="mx.com.develop.store.model.Venta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Develop Store: Listado de Productos</title>
        <style type="text/css">
            #table { 
                border-collapse: collapse; 
            }
        </style>
    </head>
    <body>
        <table border='0' cellpadding='5' cellspacing='0' width='800'> 
            <tr bgcolor='#3882C7' align='center' valign='center' height='20'> 
                <td>
                    <h3><font color="white">Develop Store: Detalles de la compra.</h3>
                </td> 
            </tr> 
            <tr align='right'> 
                <td>
                    <table>
                        <tr>
                            <td></td>
                            <td>
                                Usuario: ${cliente.nombre} <a href="../logout.do">Salir</a>
                            </td>
                        </tr>
                    </table>
                </td> 
            </tr> 
        </table>
        <b>Usted está aquí:</b> <a href="../index.jsp">Inicio</a>/Detalles de la compra.
        <h2>La compra se realizó con éxito, aquí los detalles:</h2>
        <b>Compra realizada desde:</b>${header["user-agent"]}
        <br/>
        <b>ID de la compra:</b>${cookie["JSESSIONID"].value}
        <h2>Lista de Productos:</h2>
        <table border="1" width="800" id="table">
            <thead>
                <tr bgcolor='#3882C7'>
                    <th>No.</th>
                    <th>Descripción</th>
                    <th>Tipo</th>
                    <th>Color</th>
                    <th>Talla</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int i = 0;
                    Venta venta = (Venta) pageContext.findAttribute("venta");
                    if (venta != null) {
                        Map<Producto, Integer> productosVenta = venta.getProductos();
                        Collection<Producto> productos = productosVenta.keySet();
                        for (Producto producto : productos) {
                            i++;
                %>
                <tr id="td">
                    <td><%= i%></td>
                    <td><%= producto.getDescripcion()%></td>
                    <td><%= producto.getTipo().getTitulo()%></td>
                    <td><%= producto.getColor().getTitulo()%></td>
                    <td><%= producto.getTalla()%></td>
                    <td>$<%= producto.getPrecio()%></td>
                    <td><%= productosVenta.get(producto)%></td>                    
                </tr>
                <%
                        }
                    }
                %>
                <c:set scope="page" var="subtotal" value="${total div 1.16}" />
                <c:set scope="page" var="iva" value="${total - (total div 1.16)}" />
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><b>Subtotal:</b></td>
                    <td><d:moneda precio="${subtotal}" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><b>I.V.A.:</b></td>
                    <td><d:moneda precio="${iva}" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><b>Total:</b></td>
                    <td><d:moneda precio="${total}" /></td>
                </tr>
            </tbody>
        </table>
        <%--${cliente.nombres}--%>
        <%--<c:remove var="iva" />--%>
        <%--<c:catch var="errorFactura">--%>
        <c:set target="${factura}" property="subtotal" value="${subtotal}" />
        <c:set target="${factura}" property="iva" value="${iva}" />
        <c:set target="${factura}" property="total" value="${total}" />
        <%--</c:catch>--%>
        <b>Detalles del error: </b>${errorFactura}
        <h2>Detalles de la factura:</h2>
        <b>Folio: </b>${factura.folio}<br/>
        <b>Cliente: </b>${factura["cliente"].nombre}<br/>
        <b>Dirección: </b>${factura.cliente["direccion"]}<br/>
        <b>Subtotal: </b>${factura.subtotal}<br/>
        <b>I.V.A.: </b>${factura.iva}<br/>
        <b>Total: </b>${factura.total}<br/>
        <p>Los siguientes cupones tienen descuentos en tus próximas compras:</p>
        <%--<ul>
            <li>${cupones[0]}</li>
            <li>${cupones["1"]}</li>
        </ul>
        --%>
        <ul>
            <%--<c:forTokens items="${cupones}" delims="," var="cup">--%>
            <d:myForTokens arreglo="${cupones}" delimitador="," varName="cup">
                <li>${cup}</li>
            </d:myForTokens>
            <%--</c:forTokens>--%>
        </ul>
        <ul>
            <d:cupones cupon_uno="sdajhk56456" cupon_dos="sdf78996896" cupon_tres="654sdfgsd" cupon_cuatro="56sd456sdaf" cupon_cinco="56sdf46" />
        </ul>
        <p> <a href="../lista_productos.view">Seguir comprando</a></p>
    </body>
</html>
