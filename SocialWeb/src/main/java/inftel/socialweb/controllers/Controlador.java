package inftel.socialweb.controllers;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import inftel.socialweb.facade.UsuarioRepository;
import inftel.socialweb.googleservices.GoogleServices;
import inftel.socialweb.model.Grupo;
import inftel.socialweb.model.Publicacion;
import inftel.socialweb.model.Usuario;
import inftel.socialweb.services.GrupoServices;
import inftel.socialweb.services.PublicacionServices;
import inftel.socialweb.services.UsuarioServices;


@Controller
public class Controlador {
	
	
	@Autowired
    UsuarioServices userService;
	
	@Autowired
	GrupoServices grupoService;
	
	@Autowired
	PublicacionServices publicacionService;
	
	@Autowired
	SessionComponent session;
	
	@Autowired
	GoogleServices googleservices;
	
	private List<Publicacion> publicaciones;
	private List<Usuario> usuarios;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String inicio (HttpServletRequest request, Model modelo) {
		try {
		session = (SessionComponent) request.getSession().getAttribute("session");
		if(session.getValidate()) {
			return "principal";
		}}catch (NullPointerException e) {
		  System.out.println("NO HAY SESION");
		}
		return "inicio";
	}
	
	@RequestMapping(value = "/principal", method = RequestMethod.GET)
	public String principal (HttpServletRequest request, Model modelo) {
		session = (SessionComponent) request.getSession().getAttribute("session");
		if(session == null || session.isEmpty()) {
			return "inicio";
		}
		usuarios = session.getUsuario().getUsuarioList();
		usuarios.add(session.getUsuario());
		
		try {
			
			publicaciones =  publicacionService.findPublicacionesAmigos(usuarios);
			
		}catch(Exception e){
			modelo.addAttribute("publicaciones",null);	
		}
		

		modelo.addAttribute("uSession",session.getUsuario());
		modelo.addAttribute("publicaciones", publicaciones);
		modelo.addAttribute("session",session);	
		return "principal";
		
	}
	
	@RequestMapping(value = "/perfil", method = RequestMethod.GET)
	public String perfil (HttpServletRequest request, Model modelo) {
		session = (SessionComponent) request.getSession().getAttribute("session");
		if(session == null || session.isEmpty()) {
			return "inicio";
		}
		modelo.addAttribute("uSession",session.getUsuario());
		modelo.addAttribute("publicaciones", session.getUsuario().getPublicacionList());
		modelo.addAttribute("session",session);
	    return "perfil_usuario";
	}
	

	// ---- BUSCAR AMIGO ---- 
	
	@RequestMapping(value="/visitarPerfil/{idUsuario}", method=RequestMethod.GET)
	public String visitarPerfil (HttpServletRequest request,Model modelo, @PathVariable("idUsuario") BigDecimal idUsuario) {
		session = (SessionComponent) request.getSession().getAttribute("session");
		if(session == null || session.isEmpty()) {
			return "inicio";
		}
		Usuario usuario =  this.userService.findById(idUsuario);
		publicaciones = usuario.getPublicacionList();
		modelo.addAttribute("amigo", usuario);
		modelo.addAttribute("grupos", session.getUsuario().getGrupoList());
		modelo.addAttribute("publicaciones", publicaciones);
		
		return "perfil_amigo";
	}


	
}
	
	
	
	
	
	
