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
     
	            <img class="mx-auto rounded-circle" src="${uSession.fotoperfil}" alt="">
	            	<h4> ${uSession.nombre} ${uSession.apellidos} </h4>
	            	<h5> ${uSession.direccion} </h5>
	            	<hr class="mb-4">
	            <ul class="list-inline social-buttons">
	              <li class="list-inline-item">
	                <a href="/doOutSession">
	                  <i class="fas fa-door-open"></i>
	                </a>
	              </li>
	              <li class="list-inline-item">
	                <a href="/editarUsuario">
	                  <i class="fas fa-file-signature"></i>
	                </a>
	              </li>
	            </ul>
		
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

        <!-- Categories Widget -->
        <div class="card my-4">
          <h5 class="card-header">Mis grupos</h5>
          <div class="card-body">
            <div class="row">
              <div class="col-lg-6">
              <c:forEach items="${grupos}" var="grupo">
                <ul class="list-unstyled mb-0">
                  <li>
                  	<button id="myBtn"> ${grupo.nombreGrupo}</button>
                  </li>
                  <li>
                </ul>
                </c:forEach>
                <a href="" ></a>
              </div>
            </div>
          </div>
        </div>
		
        </div>	
        
      <!-- Vista de los Grupos -->  
      
      <div id="myModal" class="modal fade" role="dialog" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
      <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
		
		
	 </div>

      <!-- Blog Entries Column -->
      <div class="col-md-8">

        <h1 class="my-4">Mis publicaciones </br>
          <small>Posturea posturea</small>
        </h1>
        
        <c:if test="${publicaciones.isEmpty()}">
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
		          <div class="card-footer text-muted team-member" style="margin-bottom: 0px">
		          	 <ul class="list-inline social-buttons">
		          	 	<li class="list-inline-item" >
		          	 		<a href="/borrarPublicacion/${publicacion.idPublicacion}">
			                  <i class="fa fa-times-circle"></i>
			                </a>
		          	 	</li>
		          	 </ul>
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
    
</section>


<!-- Bootstrap core JavaScript -->
  <script src="theme_perfil_publicacion/vendor/jquery/jquery.min.js"></script>
  <script src="theme_perfil_publicacion/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


<jsp:include page="botton.jsp" flush="true" />   