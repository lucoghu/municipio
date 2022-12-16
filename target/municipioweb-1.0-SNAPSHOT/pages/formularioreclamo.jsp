<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reclamo</title>
    
</head>
<body>
    <h1>Formulario de Reclamo</h1>
    <form action="${pageContext.request.contextPath}/ReclamoServlet?accion=insertar" method="post"> 
       <label for="domi">Domicilio:</label>
       <input type="text" id="domi" name="domicilio" placeholder="ingrese domicilio" >

       <h4>Elija una categoria</h4>
     <!--  <label for="alum">alumbrado:</label>
       <input type="checkbox" id="alum" name="alumbrado" >
       <label for="arb">arbolado:</label>
       <input type="checkbox" id="arb" name="arbolado" >
       <label for="lim">limpieza:</label>
       <input type="checkbox" id="lim" name="limpieza" >
       <label for="plu">pluvial:</label>
       <input type="checkbox" id="plu" name="pluvial" ><br><br>  -->
    <fieldset>
       <label>Alumbrado
       <input type="radio" name="categoria" value="alumbrado">
       </label><br>
       
       <label>Arbolado
       <input type="radio" name="categoria" value="arbolado">
       </label><br>
        
       <label>Limpieza
       <input type="radio" name="categoria" value="limpieza">
       </label><br>
       
       <label>Pluvial
       <input type="radio" name="categoria" value="pluvial">
       </label><br>
      </fieldset>
     
       <p>Describa su Reclamo</p>
       <textarea name="descripcion" rows="10" cols="50"></textarea>
       <br><br>
       
       <!-- se debería mostrar en fecha actual en el formulario, pero no la muestra, cuando jsp pasa por servlet no toma css-->
     <!--  <label for="fr">Fecha del Reclamo:</label>
       <input type="date" id="fr" disabled="disabled"  name="fechaReclamo" required>
         <br><br> -->
       
       <input type="submit" value="Aceptar">
       <input type="reset" value="Blanquear">

    </form>


</body>
</html>
