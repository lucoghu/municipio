<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html5>
<%--<%
    if (session.getAttribute("idpersona") != null) {
%> --%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reclamos</title>

    <link rel="stylesheet" href="../css/estilo.css"/>
</head>
<body>
    <h1>Municipio Web - Reclamos</h1>
    <div class="linea"></div>
    
    <p class="Bienvenido">Bienvenido : ${usuariosesion} </p>
    <%--Bienvenido : <%=request.getSession().getAttribute("usuariosesion")%>  --%>
     <nav>
        <ul class="menu">
            <li> <a href="ReclamoServlet?accion=listaruser">Mis Reclamos</a> </li>
            <li><a href="pages/formularioreclamo.jsp">Nuevo Reclamo</a></li>
        </ul> 
    </nav>
    
  <form action="ReclamoServlet?accion=cerrar" method="get">
      <input type="submit" value="Cerrar Sesión">
   </form> 
</body>
</html> 
<%--<%
    } else {
        response.sendRedirect("login.jsp");
    }
%> --%>