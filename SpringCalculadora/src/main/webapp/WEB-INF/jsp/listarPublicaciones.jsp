<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>PUBLICACIONES</h1>
	<table>
	<tr>
		<th>COMENTARIO</th>
		<th>FECHA_PUBLICACION</th>	
		<th>USUARIO</th>			
	</tr>
	<c:forEach items="${publicaciones}" var="publicacion">
	<tr>
		<td>
		${publicacion.idPublicacion}
		</td>
		<td>
		${publicacion.comentario}
		</td>
		<td>
		${publicacion.fechaPublicacion}
		</td>
		<td>
		${publicacion.usuarioId}
		</td>	
		<td>
		<a href="/borrarPublicacion/${publicacion.idPublicacion}">Borrar</a>
		</td>	
		<td>
		<a href="/editarPublicacion/${publicacion.idPublicacion}">Editar</a>
		</td>
	</tr>
	</c:forEach>
			<td>
		<a href="/nuevoPublicacion ${publicacion.idPublicacion}">NUEVA PUBLICACION</a>
		</td>
	</table>
</body>
</html>