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
<h1>GRUPOS</h1>
	<table>
	<tr>
		<th>NOMBRE</th>
		<th>FECHA_CREACION</th>		
	</tr>
	<c:forEach items="${grupos}" var="grupo">
	<tr>
		<td>
		${grupo.nombreGrupo}
		</td>
		<td>
		${grupo.fecha}
		</td>	
		<td>
		<a href="/borrarGrupo/${grupo.idGrupo}">Borrar</a>
		</td>	
		<td>
		<a href="/editarGrupo/${grupo.idGrupo}">Editar</a>
		</td>
	</tr>
	</c:forEach>
			<td>
		<a href="/nuevoGrupo ${grupo.idGrupo}">NUEVO GRUPO</a>
		</td>
	</table>
</body>
</html>