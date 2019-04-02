package inftel.socialweb.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

import inftel.socialweb.model.Usuario;
import inftel.socialweb.services.UsuarioServices;

@Controller
public class SessionControl {
	
	
	@Autowired
	UsuarioServices usuarioservices;
	
	@Autowired
	SessionComponent session;
	Boolean sessionError;
	
	// ----- INICIAR SESION -----
	
	@RequestMapping(value = "/doSession", method = RequestMethod.POST)
	public String doSession (HttpServletRequest request, Model modelo,@RequestParam("mail") String mail, @RequestParam("contrasena") String contrasena) throws FileNotFoundException, ScriptException, IOException, NoSuchMethodException {
			
			
			if(mail.isEmpty() || contrasena.isEmpty()) {
				sessionError = false;
				return "inicio";
			}else {
				session.setUsuario(usuarioservices.findByCorreoAndContrasena(mail,contrasena));
				
					if(session.getUsuario()!=null) {
						session.setValidate(true);
						request.getSession().setAttribute("session",session);
						
						return "redirect:/principal";
					}else {
						sessionError = true;
						session.setValidate(false);
						return "redirect:/";
					}
				
				
			}
			
		
	}
	
	// ----- CERRAR SESION -----
	
	@RequestMapping(value = "/doOutSession", method = RequestMethod.GET)
	public String doOutSession (HttpServletRequest request) {
		request.getSession().invalidate();
		request.getSession().removeAttribute("session");
		System.out.println(request.getSession().getAttribute("session"));
		return "inicio";
	}
	

}
