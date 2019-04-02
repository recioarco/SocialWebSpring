<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

 
 
 
<!DOCTYPE html>
<html>

<head>



<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Esto es necesario para el Chat -->



<!-- Esto ya es la cabecera de la página que aparece en todas las secciones -->


  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Esto NO es facebook</title>
  
  <!-- Bootstrap core CSS -->
  <link href="/theme_inicio/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/theme_inicio/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="/theme_inicio/css/agency.min.css" rel="stylesheet">
  <link href="/theme_inicio/css/chatstyle.css" rel="stylesheet">
  <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>

</head>

<body id="page-top">

  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
    <div class="container">
   
   <c:choose> 
   
    <c:when  test = "${session.getValidate()}">
      <a class="navbar-brand js-scroll-trigger"  href="/principal">PopUp</a>
      <!-- Dependiendo de si tengo o no la sesión iniciada muestra cosas distintas -->
      
		     <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        		Menu
        		<i class="fas fa-bars"></i>
      		</button>
		      
		      <div class="collapse navbar-collapse" id="navbarResponsive"> 
		        <ul class="navbar-nav text-uppercase ml-auto">
		          <li class="nav-item">
		            <a class="nav-link js-scroll-trigger" href="/principal">Inicio</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link js-scroll-trigger" href="/perfil">Mi perfil</a>
		          </li>
		          <li class="nav-item">
		            <a class="nav-link js-scroll-trigger" href="/amigos">Amigos</a>
		          </li>    
		        </ul>
		      </div>
		    </div>
    </c:when>
  	<c:otherwise>
  	
  	<a class="navbar-brand js-scroll-trigger"  href="/inicio">PopUp</a>
      <!-- Dependiendo de si tengo o no la sesión iniciada muestra cosas distintas -->
  	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        		Menu
        		<i class="fas fa-bars"></i>
      		</button>
		      
		      <div class="collapse navbar-collapse" id="navbarResponsive">
		        <ul class="navbar-nav text-uppercase ml-auto">
		        
		          <form class="form-inline" action="/doSession" method="POST" >
		          
		          <li class="nav-item">
		            <input class="form-control mr-sm-2" name="mail" id="email" type="text" placeholder="Email *" required="required" data-validation-required-message="Introduzca su dirección de correo"> 
		          </li>
		          
		          <li class="nav-item">
		            <input class="form-control mr-sm-2" name="contrasena" id="contraseña" type="password" placeholder="Contraseña *" required="required" data-validation-required-message="Contraseña"> 
		          </li>
          		
          		  
          		 
          		  <button id="sendMessageButton" class="btn btn-primary mr-sm-2" type="submit">Iniciar sesión</button>
          		</form>
		        </ul>
				
		    </div>
   	</c:otherwise>
    </c:choose>
  </nav>
  
  