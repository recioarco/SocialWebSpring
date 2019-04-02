<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Incluye el encabezado descrito en top -->    
<jsp:include page="top.jsp" flush="true" />

<section id="contact">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="section-heading text-uppercase">Regístrate</h2>
          <h3 class="section-subheading text-muted">Rellena el formulario con tus datos.</h3>
          <h4 class="section-subheading text-muted">Recuerda! salir a la calle y hablar cara a cara sigue siendo importante.</h4>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-12">
         <!-- Aquí empieza el formulario -->
          <form  method="post" action="/registrar">
             <div class="row">
              <div class="col-md-6">
                <div class="form-group">
                
                   <input name="nombre"  class="form-control"  type="text" placeholder="Nombre *" required="required" data-validation-required-message="Por favor, introduzca su nombre.">
                  <p class="help-block text-danger"></p>
                </div>
                <div class="form-group">
                  <input name="apellidos" class="form-control" type="text" placeholder="Apellidos *" required="required" data-validation-required-message="Por favor, introduzca sus apellidos.">
                  <p class="help-block text-danger"></p>
                </div>
                <div class="form-group">
                  <input name="correo" class="form-control" type="text" placeholder="Email *" required="required" data-validation-required-message="Por favor, introduzca su email.">
                  <p class="help-block text-danger"></p>
                </div>
                <div class="form-group">
                  <input name="nacimiento" class="form-control" type="date" placeholder="Fecha de nacimiento *" required="required" data-validation-required-message="Por favor, indique la fecha de nacimiento.">
                  <p class="help-block text-danger"></p>
                </div>
                <div class="form-group">
                  <input name="direccion" class="form-control" type="text" placeholder="Direccion *" required="required" data-validation-required-message="Por favor, indique su sexo.">
                  <p class="help-block text-danger"></p>
                </div>
                <div class="form-group">
                  <input name="sexo" class="form-control" type="text" placeholder="Sexo *" required="required" data-validation-required-message="Por favor, indique su sexo.">
                  <p class="help-block text-danger"></p>
                </div>
                <div class="form-group">
                  <input name="password" class="form-control" 
                   type="text" placeholder="Contraseña *" type="password" required="required" data-validation-required-message="url">
                  <p class="help-block text-danger"></p>
                </div>
                 <div class="form-group">
                  <input  class="form-control" 
                   type="text" placeholder="Repite contraseña *" type="password" required="required" data-validation-required-message="url">
                  <p class="help-block text-danger"></p>
                </div>
                
              </div>
              
              <div class="clearfix"></div>
              <div class="col-lg-12 text-center">
                <div id="success"></div>
                <input id="sendMessageButton" class="btn btn-primary btn-xl text-uppercase" value="Enviar" type="submit">
              </div>
            </div>
            
          </form>
        </div>
      </div>
    </div>
  </section>

<jsp:include page="botton.jsp" flush="true" />  
  
  