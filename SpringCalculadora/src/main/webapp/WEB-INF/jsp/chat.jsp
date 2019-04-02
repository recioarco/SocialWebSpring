<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<div class="team-member popup">
 	<form id="usernameForm" name="usernameForm">
 		<c:set var="username" value="${session.getUsuario().correo}" />
        <script> var username ="${username}"; </script>
 		<ul class="list-inline-items chat-buttons">
            <li class="list-inline-item">
              <a href="javascript:void(0)"  onclick="openForm_conver(),connect()">
                <i class="fab fa-rocketchat"></i>
              </a>
            </li>
        </ul>
     </form>
  </div>
   <!-- <button class="fas fa-user-friends" onclick="openForm()">Chat</button>
  
  <!-- Esto es el chat, bueno una prueba del chat -->
 
</section>
 
  
  <!-- Esto es el chat, bueno una prueba del chat -->

<div class="chat-popup" id="myForm_conversacion">
 <div class="chat-container" id="chat-container" >
  <div class="card my-4" >
  
  		<div class="chat-header">
  		 	<div class="card-header">
                <h5>Chat</h5>
                <div class="user-container">
  
            </div>
            </div>
         </div>
 
            <div class="card-body">
            <ul id="messageArea">

            </ul>
            <form id="messageForm" name="messageForm" nameForm="messageForm">
                <div class="form-group">
                    <div class="input-group clearfix">
                        <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                        <button type="button" onclick="sendMessage()" class="btn btn-group-primary">Send</button>
						<button type="button" class="btn btn-group-primary" onclick="closeForm_conver(), disconnect()">Close</button>
                    </div>
                </div>
            </form>
       </div>
   </div>
  </div>
</div>