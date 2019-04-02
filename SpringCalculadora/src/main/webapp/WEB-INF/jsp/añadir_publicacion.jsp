<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 
 <!-- Esta además usa un estilo nuevo -->
  <link href="theme_perfil_publicacion/css/blog-home.css" rel="stylesheet">

<jsp:include page="top.jsp" flush="true" />

  <!-- Blog Post -->
        <div class="card mb-4">
          <img class="card-img-top" src="http://placehold.it/750x300" alt="Card image cap">
          <div class="card-body">
            <h2 class="card-title">Nueva publicación</h2>
            <form action="/UpComentario" method="POST" enctype="multipart/form-data">
				<p class="card-text">
				<input name="comentario" type="text">
				
				<input name="uploadfile" type="file">
				<button>Enviar</button>
				</p>
			</form>
          </div>
        </div>





<jsp:include page="botton.jsp" flush="true" />