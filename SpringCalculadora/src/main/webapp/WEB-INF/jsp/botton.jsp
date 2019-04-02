<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<footer>
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <span class="copyright">Copyright &copy; PopUp 2019</span>
        </div>
        <div class="col-md-4">
          <ul class="list-inline quicklinks">
            <li class="list-inline-item">
              <a href="#">Privacy Policy</a>
            </li>
            <li class="list-inline-item">
              <a href="#">Terms of Use ${session.getValidate()}</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
</footer>

  

  <!-- Bootstrap core JavaScript -->
  <script src="/theme_inicio/vendor/jquery/jquery.min.js"></script>
  <script src="/theme_inicio/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Plugin JavaScript -->
  <script src="theme_inicio/vendor/jquery-easing/jquery.easing.min.js"></script>
  

  <!-- Contact form JavaScript -->
  <script src="/theme_inicio/js/jqBootstrapValidation.js"></script>
  <script src="/theme_inicio/js/contact_me.js"></script>
  <script src="/theme_inicio/js/bootbox.min.js"></script>
  <script src="/theme_inicio/js/bootbox.locales.min.js"></script>

  <!-- Custom scripts for this template -->
  
  <script type="text/javascript" src="/theme_inicio/js/agency.min.js"></script>
  <script type="text/javascript" src="/theme_inicio/js/chat_menu.js"></script>
  <script type="text/javascript" src="/theme_inicio/js/chat_f.js"></script>
  <script type="text/javascript" src="/theme_inicio/js/jquery.js"></script>  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

</body>

</html>
