<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<jsp:include page="top.jsp" flush="true" />





<section class="bg-light"  id="portfolio">
 <!-- Page Content -->
  <div class="container">

    <div class="row">
    
     <div class="col-sm-4">
          <div class="team-member">
     
	            <img class="mx-auto rounded-circle" src="${amigo.fotoperfil}" alt="">
	            	<h4> ${amigo.nombre} ${amigo.apellidos} </h4>
	            	<h5> ${amigo.direccion} </h5>
	            	<hr class="mb-4">	            
		
		</div>
        
         <!-- Side Widget -->
     

        </div>	
        
      <!-- Vista de los Grupos -->  


      <!-- Blog Entries Column -->
      <div class="col-md-8">

        <h1 class="my-4">Publicaciones </br>
          
        </h1>
        
        <c:if test="${publicaciones.isEmpty()}">
			<div class="col-md-8">

		        <!-- Blog Post -->
		        <div class="card mb-4">
		          <img class="card-img-top" src="mis_imagenes/IMG_5136.jpg" alt="Card image cap">
		          <div class="card-body">
		            <h2 class="card-title">No hay publicaciones</h2>
		            <p class="card-text">Todavía no ha publicado nada...</p>
		          </div>
		        </div>
        	</div>
		</c:if>
        
        
        
		<c:forEach items="${publicaciones}" var="publicacion">
        <!-- Miro si hay o no hay publicaciones -->
	        
	
			 <!-- Blog Post -->
		        <div class="card mb-4">
		          <c:choose>
		          <c:when test = "${empty publicacion.url}">
		          </c:when>
		          <c:otherwise> 
		          <img class="card-img-top" src="${publicacion.url}" alt="PUBLICACION">
		          </c:otherwise>
		          </c:choose>
		          <div class="card-body">
		            <h2 class="card-title">${publicacion.usuarioId.nombre}</h2>
		            <p class="card-text">${publicacion.comentario}</p>
		          </div>
		          <div class="card-footer text-muted">
		            ${publicacion.fechaPublicacion}
		          </div>
		     
		        </div>
			

		</c:forEach>
		</div>

    </div>
    <!-- /.row -->
     </div>
  <!-- /.container -->
    <c:if test = "${session.getValidate()}">
	<jsp:include page="chat.jsp" flush="true" />  
    </c:if> 
  </div>
    
</section>



<jsp:include page="botton.jsp" flush="true" />