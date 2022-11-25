<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar Persona</title>
    <link rel="stylesheet" href="../css/estilo.css"/>
</head>
<body>
    <div class="contenedor_registro">
        <h3>Formulario de Actualización de Persona</h3>
       
       <form action="${pageContext.request.contextPath}/PersonaServlet?accion=actualizar" method="get">
         <input type="hidden" id="idp" name="idpersona" value="${personaencontrada.idpersona}">
           
         <label for="nom">Nombre:</label>
         <input type="text" id="nom" name="nombre"  value="${personaencontrada.nombre}">
         <br><br>
         
         <label for="ape">Apellido:</label>
         <input type="text" id="ape" name="apellido"  value="${personaencontrada.apellido}" required="">
         <br><br>

          <label for="dni">Dni:</label>
         <input type="text" id="dni" name="dni"  value="${personaencontrada.dni}" required="">
         <br><br>
    
         <label for="ml">Mail:</label>
         <input type="email" id="ml" name="mail" value="${personaencontrada.mail}" placeholder="tucorreo@mail.com">
         <br><br>

         <label for="tlf">Telefono:</label>
         <input type="tel" id="tlf" name="telefono" value="${personaencontrada.telef}">
         <br><br>
         
         <input id="boton" type="submit" value="Actualizar">
  
       </form>
     </div>
</body>
</html>