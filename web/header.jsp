<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/store.develop.com.mx" prefix="d" %>
<table border='0' cellpadding='5' cellspacing='0' width='800'> 
    <tr bgcolor='#3882C7' align='center' valign='center' height='20'> 
        <td>
            <h3><font color='white'>Develop Store: ${param.titulo}</h3>
        </td> 
    </tr> 
    <tr align='right'> 
        <td>
            <table>
                <tr>

                    <td>Usuario: </td>
                    <td>
                        <%--<c:choose>
                            <c:when test="${cliente eq null}">
                                <b>Invitado</b>
                            </c:when>
                            <c:otherwise>
                                ${cliente.nombre} <a href="logout.do">Salir</a>
                            </c:otherwise>
                        </c:choose>--%> 
                                
                        <d:myIf condicion="${cliente eq null}">
                            <d:then>
                                <b>Invitado</b>
                            </d:then>
                            <d:else>
                                ${cliente.nombre} <a href="logout.do">Salir</a>
                            </d:else>
                        </d:myIf>
                    </td>
                </tr>
            </table>
        </td> 
    </tr> 
</table>