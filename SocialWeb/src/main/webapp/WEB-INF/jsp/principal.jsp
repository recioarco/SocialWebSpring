<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<jsp:include page="top.jsp" flush="true" />


<section class="bg-light" id="portfolio">
 <!-- Page Content -->
  <div class="container">

    <div class="row">

      <!-- Blog Entries Column -->
      <div class="col-md-8">

        <h1 class="my-4">Mira lo que dicen tus amigos
          <small>Recuerda, la mitad de lo que ves es mentira</small>
        </h1>
		<c:forEach items="${publicaciones}" var="publicacion">
		
		<!-- Miro si hay o no hay publicaciones -->
		<!-- No hay publicaciones -->
		<c:choose>
			
			<c:when test="${empty publicaciones.size()}">
			<div class="col-md-8">

		        <!-- Blog Post -->
		        <div class="card mb-4">
		          <img class="card-img-top" src="mis_imagenes/nopubli.PNG" alt="Card image cap">
		          <div class="card-body">
		            <h2 class="card-title">No hay publicaciones</h2>
		            <p class="card-text">O bien no tienes muchos a migos, o tus amigos cuentan pocas cosas...</p>
		          </div>
		        </div>
        	</div>
			
			</c:when>
			
					
			<c:otherwise>
			
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
			
			
			</c:otherwise>

		</c:choose>

       
		</c:forEach>
		</div>

       <!-- Sidebar Widgets Column -->
      <div class="col-md-4">

        <!-- Search Widget -->
         <div class="card my-4">
          <h5 class="card-header">Busca entre tus amigos</h5>
          <div class="card-body">
            <div class="form-group">
            
             
             <form action="/buscar" method="POST">
              <input name="busqueda" type="text" class="form-control" placeholder="Buscar..."> 
              <hr class="mb-4">
              	  <div class="btn-group">           
              		<button class="btn btn-primary" >Cotilleo cotilleo!</button>
              	</div>
            </form>
            	</div>
            
          </div>
        </div>

        

        <!-- Side Widget -->
        <div class="card my-4">
          <form action="/UpPublicacion" method="POST" enctype="multipart/form-data">
          <h5 class="card-header"> Nueva publicación</h5>
          <div class="card-body">
         
					<div class="form-group">
					    <label for="exampleFormControlTextarea1">Tu nuevo post :D</label>
					    <textarea class="form-control" name="comentario" id="exampleFormControlTextarea1" rows="3" required="required"></textarea>
					  </div>
					<hr class="mb-4">
			        <div class="btn-group">
		                <button class="btn btn-default">Subir</button>
		                <label class="btn btn-primary">
                    Buscar&hellip; <input type="file" name="uploadfile" style="display: none;" required="required">
			        </div>

       
          </div>
           </form>
      </div>
	</div>
    </div>
    <!-- /.row -->
    <c:if test = "${session.getValidate()}">
	<jsp:include page="chat.jsp" flush="true" />  
    </c:if> 
  </div>
  <!-- /.container -->
    
</section>


<!-- Bootstrap core JavaScript -->
  <script src="theme_perfil_publicacion/vendor/jquery/jquery.min.js"></script>
  <script src="theme_perfil_publicacion/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


			
<jsp:include page="botton.jsp" flush="true" />