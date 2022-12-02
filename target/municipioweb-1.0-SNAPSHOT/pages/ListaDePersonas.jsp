<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
       <meta charset="UTF-8">
       <meta http-equiv="X-UA-Compatible" content="IE=edge">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
        <title>JSP Page</title>
        <link rel="stylesheet" href="../css/estilo.css"/>
    </head>
    <body>
        <h1>Municipio Web</h1>
        <div class="linea"></div>
         <p class="Bienvenido">Bienvenido : ${usuariosesion} </p>
        <h1>PANEL ADMINSTRADOR</h1>
        <%--= personas --%>
        <table border="1px solid black">
            <thead>
            <tr>
                <th class="columna">Id Persona</th>
                <th class="columna">DNI</th>
                <th class="columna">Nombre</th>
                <th class="columna">Apellido</th>
                <th class="columna">Mail</th>
                <th class="columna">Teléfono</th>
                <th class="columna">Acciones</th>
                 
              <!--  <th class="columna">User</th>
                <th class="columna">Clave</th>
                <th class="columna">Admin</th> -->
            </tr>
            </thead>
            <c:forEach var="persona" items="${ListaP}" >
                <tbody>
                <tr>
                    <td class="fila">${persona.idpersona}</td>
                    <td class="fila"> <c:out value="${persona.dni}"/></td>
                    <td class="fila">${persona.nombre}</td>
                    <td class="fila">${persona.apellido}</td>
                    <td class="fila">${persona.mail}</td>
                    <td class="fila">${persona.telef}</td>
                    <td class="fila">
                        <%-- <input type="hidden" id="codigo" value="${persona.idpersona}"> --%>
                         <a id="editarPersona" href="<c:url value="ServletPersona">
                            <c:param name="accion" value="buscaridPersona" />
                            <c:param name="idpersona" value="${persona.idpersona}"/>  </c:url>"
                            class="bot"> Editar</a>                                                   
                        <!--<button type="button" class="bot" title="Editar">Editar </button>  -->
                        
                        
                        <a id="eliminarPersona" href="<c:url value="ServletPersona">
                            <c:param name="accion" value="buscaridPersona" />
                            <c:param name="idpersona" value="${persona.idpersona}"/>  </c:url>"
                            class="bot"> Eliminar</a>
                        <!--<button type="button" class="bot" title="Eliminar">Eliminar </button> -->
                    </td>
                    <%--  <td class="fila">${persona.user}</td>
                    <td class="fila">${persona.clave}</td>
                    <td class="fila">${persona.admin}</td> --%>
                </tr>
               </tbody>
            </c:forEach>
        </table>
        <%--   <c:forEach var="persona" items="${Lista}" >
            <c:out value="${persona.nombre}" />
        </c:forEach> --%>
        <form action="cerrar" method="post">
          <input type="submit" value="Cerrar Sesión">
        </form>
    </body>
</html>
