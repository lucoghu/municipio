<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Actualizar Reclamo</title>
    <link rel="stylesheet" href="../css/estilo.css"/>
</head>
<body>
    <div class="contenedor_registro">
        <h3>Formulario de Actualización de Reclamo</h3>
       
       <form action="${pageContext.request.contextPath}/ReclamoServlet?accion=actualizar" method="post">
         <input type="hidden" id="idr" name="idreclamo" value="${reclamoencontrado.idreclamo}">
         <input type="hidden" id="idc" name="idcategoria" value="${reclamoencontrado.getCategoria().getIdcategoria()}">
          <%--<input type="hidden" id="per" name="perso" value="${reclamoencontrado.persona}">--%>
         <input type="hidden" id="idp" name="idpersona" value="${reclamoencontrado.persona.idpersona}">
        
         <label for="fechac">Fecha de creación:</label>
         <input type="date" id="fechac" name="fechadecreacion"  value="${reclamoencontrado.fechacreacion}">
         <br><br>
         
         <label for="fechar">Fecha de resolución:</label>
         <input type="date" id="fechar" name="fecharesolucion"  value="${reclamoencontrado.fecharesolucion}">
         <br><br>

          <label for="domic">Domicilio:</label>
         <input type="text" id="domic" name="domicilio"  value="${reclamoencontrado.domicilio}" required="">
         <br><br>
    
         <label for="cat">Categoria:</label>
         <input type="text" id="cat" name="categoria" value="${reclamoencontrado.getCategoria().getCategoria().toString()}">
         <br><br>

         <label for="comen">Comentario:</label>
         <input type="textarea" id="comen" name="comentario" value="${reclamoencontrado.comentario}">
         <br><br>
         
         <input id="boton" type="submit" value="Actualizar">
  
       </form>
     </div>
</body>
</html>
