<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" href="../css/estilo.css"/>
</head>
<body style="background: black">
    <h1>Municipio Web</h1>
    <div class="linea"></div>
     <div class="contenedor_login">
        <h3>Inicio de Sesión en Municipio Web</h3>
       <form action="${pageContext.request.contextPath}/PersonaServlet?accion=validar" method="post">
    
         <label for="us">Usuario:</label>
         <input type="text" id="us" name="usuario" placeholder="ingrese nombre de usuario" required>
         <br><br>
         
         <label for="cs">Contraseña:</label>
         <input type="password" id="cs" name="password" placeholder="ingrese una contraseña" required>
         <br><br>
    
         <input id="boton" type="submit" value="Aceptar">
         <input type="reset" value="Blanquear">
         

       </form>
     </div>
</body>
</html> 