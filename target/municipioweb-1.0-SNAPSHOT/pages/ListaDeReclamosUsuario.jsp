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
        
        <h1>PANEL CONTRIBUYENTE</h1>

        <table border="1px solid black">
            <thead>
            <tr>
                <th class="columna">Id Reclamo</th>
                <th class="columna">Fecha de creación</th>
                <th class="columna">Fecha de resolución</th>
                <th class="columna">Domicilio</th>
                <th class="columna">Categoria</th>
                <th class="columna">Comentario</th>
                
            </tr>
            </thead>
            <c:forEach var="reclamo" items="${ListaRU}" >
               <tbody>
                 <tr>
                     <td class="fila">${reclamo.idreclamo}</td>
                     <td class="fila">${reclamo.fechacreacion}></td>
                     <td class="fila">${reclamo.fecharesolucion}</td>
                     <td class="fila">${reclamo.domicilio}</td>
                     <td class="fila">${reclamo.getCategoria().getCategoria()}</td>
                     <td class="fila">${reclamo.comentario}</td>
                 </tr>
               </tbody>
            </c:forEach>
        </table>
    </body>
</html>
