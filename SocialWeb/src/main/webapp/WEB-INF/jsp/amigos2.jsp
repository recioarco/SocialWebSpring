<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="top.jsp" flush="true" />


 <section class="bg-light" id="team">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="section-heading text-uppercase">Mis amigos</h2>
          <h3 class="section-subheading text-muted">Lo amigos son la familia que se escoge.</h3>
        </div>
      </div>
      
      <!-- Busco entre los amigos del usuario -->

		<div class="row">
		<c:forEach items="${buscada}" var="usu"> <!-- Aquí iría en realidad usuario.getAmigos -->
        
        <c:choose>
		
    		<c:when test="${usu.getValidate()}">
    		
	    		<div class="col-sm-4">
	          	<div class="team-member">
	     
		            <img class="mx-auto rounded-circle" src="${usu.getUsuario().fotoperfil}" alt="">
		            	<h4> ${usu.getUsuario().nombre} ${usu.getUsuario().apellidos} </h4>
		            <ul class="list-inline social-buttons">
		              <li class="list-inline-item">
		                <a href="/addtogrupo/${usu.getUsuario().idUsuario}">
		                  <i class="fas fa-user-check"></i>
		                </a>
		              </li>
		              <li class="list-inline-item">
		                <a href="/eliminartoamigos/${usu.getUsuario().idUsuario}">
		                  <i class="fas fa-user-minus"></i>
		                </a>
		              </li>
		            </ul>
			
				</div>
	        </div>	
    			
    		</c:when>    
   			<c:otherwise>
   			
   			  <div class="col-sm-4">
	          	<div class="team-member">
	     
		            <img class="mx-auto rounded-circle" src="${usu.getUsuario().fotoperfil}" alt="">
		            	<h4> ${usu.getUsuario().nombre} ${usu.getUsuario().apellidos} </h4>
		            <ul class="list-inline social-buttons">
		              <li class="list-inline-item">
		                <a href="/addtoamigos/${usu.getUsuario().idUsuario}">
		                  <i class="fas fa-user-plus"></i>
		                </a>
		              </li>
		            </ul>
			
				</div>
	        </div>
   				 
    		</c:otherwise>
		</c:choose>
       </c:forEach>
     </div>
    </div>
    
  <!-- Esto es el chat, bueno una prueba del chat -->


  <c:if test = "${session.getValidate()}">
	<jsp:include page="chat.jsp" flush="true" />  
    </c:if> 
  </div>
    
  </section>
  

  

<jsp:include page="botton.jsp" flush="true" />