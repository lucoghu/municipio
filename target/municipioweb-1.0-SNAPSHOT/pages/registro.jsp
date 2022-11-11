<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registrarse</title>
    <link rel="stylesheet" href="../css/estilo.css"/>
</head>
<body>
    <div class="contenedor_registro">
        <h3>Registro en Municipio Web</h3>
       <form action="${pageContext.request.contextPath}/PersonaServlet?accion=insertar" method="get">
           <!-- comment  <input type="hidden" name="accion" value="insertar"> -->
           
         <label for="nom">Nombre:</label>
         <input type="text" id="nom" name="nombre" required="">
         <br><br>
         <label for="ape">Apellido:</label>
         <input type="text" id="ape" name="apellido" required="">
         <br><br>

         <label for="dni">Dni:</label>
         <input type="number" id="dni" name="dni">
         <br><br>

         <label for="fn">Fecha de Nacimiento:</label>
         <input type="date" id="fn" name="fecnac" required>
         <br><br>
    
         <label for="ml">Mail:</label>
         <input type="email" id="ml" name="mail" placeholder="tucorreo@mail.com">
         <br><br>

         <label for="tlf">Telefono:</label>
         <input type="tel" id="tlf" name="telefono">
         <br><br>
         
         <label for="us">Usuario:</label>
         <input type="text" id="us" name="usuario" placeholder="elija un nombre de usuario" required="">
         <br><br>
         
         <label for="cs">Contraseña:</label>
         <input type="password" id="cs" name="password" placeholder="ingrese una contraseña" required>
         <br><br>
         
         <input id="boton" type="submit" value="Aceptar">
         <input type="reset" value="Blanquear">
         
         <!--<p> Ya tengo Cuenta<a href="./login.html">Iniciar Sesión</a></p> -->
         <p> Ya tengo Cuenta<a href="page/login.html">Iniciar Sesión</a></p>
       </form>
     </div>
</body>
</html>