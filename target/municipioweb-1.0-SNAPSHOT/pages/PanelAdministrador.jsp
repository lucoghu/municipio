<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrador</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css"/>
   
         
</head>
<body>
    <h1>Municipio Web - Administrador</h1>
    <div class="linea"></div>
    <p class="Bienvenido">Bienvenido : ${usuariosesion} </p>
   <%@include file="/pages/menuAdmin.jsp"%>
    <%--<jsp:include page="pages/ListaDePersonas.jsp" />--%>
    <form action="${pageContext.request.contextPath}/PersonaServlet?accion=cerrar" method="post">
          <input type="submit" value="Cerrar Sesión">
        </form>
</body>
</html> 
