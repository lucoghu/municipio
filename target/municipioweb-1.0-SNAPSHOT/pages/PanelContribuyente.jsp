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
<body style="background: black">
    <h1>Municipio Web - Reclamos</h1>
    <div class="linea"></div>
    
    <p class="Bienvenido">Bienvenido : ${usuariosesion} </p>
    <%--Bienvenido : <%=request.getSession().getAttribute("usuariosesion")%>  --%>
    <%--Bienvenido : <% String user=(String)session().getAttribute("usuariosesion"); out.print(user); %>  --%>
   <%--  <% String id=(String)session.getAttribute("idpersona");%>  SE NECESITA OBJETO PERSONA --%>
     <!--el id "idpesrona" se lo envio al servlet en href ....?id="idpersona", o lo caputuro con:
      int idpersona=Integer.parseInt((String)sesion.getAttribute("idpersona")); -->
   
     <% String persona=(String)session.getAttribute("persona");%>
     <nav>
        <ul class="menu">
            <li> <a href="ReclamoServlet?accion=listar">Mis Reclamos</a> </li>
            <li><a href="ReclamoServlet?accion=insertar">Nuevo Reclamo</a></li>
        </ul> 
    </nav>
    
   <!-- <a href="/PersonaServlet?accion=cerrar">Cerrar Sesión</a> -->
   <!-- href creo sólo usa get, y en el servlet lo parametros los doPost, lo anterior no sirve
  se puede hacer un servlet logout y su doGet ponerlo del metodo cerraSesion, o hago un formulario para logout-->
  <form action="cerrar" method="post">
      <input type="submit" value="Cerrar Sesión">
   </form> 
</body>
</html> 
<%--<%
    } else {
        response.sendRedirect("login.jsp");
    }
%> --%>