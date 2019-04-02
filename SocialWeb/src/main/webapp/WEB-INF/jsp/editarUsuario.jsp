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
    <div class="col-md-8 order-md-1">
      <h4 class="mb-3">Tu Datos</h4>
      <form class="needs-validation" action="/guardaredicion"  method="POST" >
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="firstName">Nombre</label>
            <input type="text" class="form-control" name="nombre" id="firstName" placeholder="" value="${datosUsuario.nombre}" required="">
            <div class="invalid-feedback">
              Valid first name is required.
            </div>
          </div>
          <div class="col-md-6 mb-3">
            <label for="lastName">Apellidos</label>
            <input type="text" class="form-control" name="apellidos" id="lastName" placeholder="" value="${datosUsuario.apellidos}" required="">
            <div class="invalid-feedback">
              Valid last name is required.
            </div>
          </div>
        </div>

        <div class="mb-3">
          <label for="username">Email</label>
          <div class="input-group">
            <div class="input-group-prepend">
              <span class="input-group-text">@</span>
            </div>
            <input type="text" class="form-control" id="username" name="correo" placeholder="" value="${datosUsuario.correo}" required="">
            <div class="invalid-feedback" style="width: 100%;">
              Your username is required.
            </div>
          </div>
        </div>

        <div class="mb-3">
          <label for="email">Nacimiento <span class="text-muted">(OBLIGATORIO)</span></label>
          <input type="date" class="form-control" name="nacimiento" id="email" placeholder="14/06/2017">
          <div class="invalid-feedback">
            Please enter a valid email address for shipping updates.
          </div>
        </div>

        

        <div class="mb-3">
          <label for="address2">Dirección <span class="text-muted">(Optional)</span></label>
          <input type="text" class="form-control" name="Direccion"  placeholder="" value="${datosUsuario.direccion}">
        </div>

		<div class="mb-3">
            <label for="country">Sexo</label>
            <select class="custom-select d-block w-100" id="country" name="sexo" required="required">
              <option value="">Choose...</option>
              <option value="H">Hombre</option>
              <option value="M">Mujer</option>
              <option value="None">Prefiero no decirlo..</option>
            </select>
            <div class="invalid-feedback">
              Please select a valid country.
            </div>
          </div>
      
	    <hr class="mb-4">
	      
	    <div class="row">
	          <div class="col-md-6 mb-3">
	            <label for="firstName">Contraseña</label>
	            <input type="text" class="form-control" name="password" id="firstName" type="password" placeholder="" value="${datosUsuario.password}"  required="" >
	            <div class="invalid-feedback">
	              Valid first name is required.
	            </div>
	          </div>
	    </div>
        
        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Guardar datos</button>
      </form>
    </div>
  
    <div class="col-sm-4">
          <div class="team-member">
     
	            <img class="mx-auto rounded-circle" src="${datosUsuario.fotoperfil}" alt="">
	            	<h4> ${datosUsuario.nombre} ${datosUsuario.apellidos} </h4>
	            	<hr class="mb-4">
	            <form class="card p-2" action="/upfotoperfil" method="POST" enctype="multipart/form-data">
			        <h5> Cambiar foto de perfil ;)</h5>
			        <hr class="mb-4">
			        <div class="btn-group">
		                <button class="btn btn-default">Subir</button>
		                <label class="btn btn-primary">
                    Buscar&hellip; <input type="file" name="uploadfile" style="display: none;" required="required">
			        </div>
			      </form>	
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
  
  <script>
  $(function() {

	  // We can attach the `fileselect` event to all file inputs on the page
	  $(document).on('change', ':file', function() {
	    var input = $(this),
	        numFiles = input.get(0).files ? input.get(0).files.length : 1,
	        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
	    input.trigger('fileselect', [numFiles, label]);
	  });

	  // We can watch for our custom `fileselect` event like this
	  $(document).ready( function() {
	      $(':file').on('fileselect', function(event, numFiles, label) {

	          var input = $(this).parents('.input-group').find(':text'),
	              log = numFiles > 1 ? numFiles + ' files selected' : label;

	          if( input.length ) {
	              input.val(log);
	          } else {
	              if( log ) alert(log);
	          }

	      });
	  });
	  
	});
  </script>

			
<jsp:include page="botton.jsp" flush="true" />