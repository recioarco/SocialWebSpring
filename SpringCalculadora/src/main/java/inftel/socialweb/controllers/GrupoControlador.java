package inftel.socialweb.controllers;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class GrupoControlador {
	
	
	@Autowired
    UsuarioServices userService;
	
	@Autowired
	GrupoServices grupoService;
	
	@Autowired
	PublicacionServices publicacionService;
	
	@Autowired
	GoogleServices googleservices;
	
	@Autowired
	SessionComponent session;
	
	BigDecimal id;
	
	private Grupo grupo;
	
	
	
	@RequestMapping(value = "/grupos", method = RequestMethod.GET)
	public String listarGrupos (Model modelo) {
		modelo.addAttribute("grupos", this.grupoService.getAllGrupos());
		return "listarGrupos";
	}
	
	
	@RequestMapping(value = "/guardarG", method = RequestMethod.POST)
	public String nuevoGrupo (Model modelo, @ModelAttribute("datosGrupo") Grupo grupo) {
		
		System.out.print(""+grupo.getIdGrupo());
		if (grupo.getIdGrupo()==null) {
			System.out.print("ESTOY CREANDO");
			grupo.setFecha(new Date());
			this.grupoService.addGrupo(grupo);
			
				
		} else {
			System.out.print("ESTOY EDITANDO");
			this.grupoService.editGrupo(grupo);	
			
		}
		
		return "redirect:/grupos";
	}
	
	
	@RequestMapping(value = "/nuevoGrupo", method = RequestMethod.GET)
	public String nuevoGrupo (Model modelo) {
		modelo.addAttribute("datosGrupo", new Grupo());
		return "registro_grupo";
	}
	
	
	
	@RequestMapping(value = "/editarGrupo/{idGrupo}", method = RequestMethod.GET)
	public String editarGrupo (Model modelo, @ModelAttribute("datosGrupo") Grupo grupo, @PathVariable("idGrupo") BigDecimal idGrupo) {
		Grupo g = this.grupoService.findByIdGrupo(idGrupo);
		System.out.println(g.getIdGrupo());
		this.grupoService.editGrupo(g);
		return "registro_grupo";
	}
	
	@RequestMapping(value = "/borrarGrupo/{idGrupo}", method = RequestMethod.GET)
	public String borrarPublicacion (Model modelo, @PathVariable("idGrupo") BigDecimal idGrupo) {
		this.grupoService.removeGrupo(idGrupo);
		return "redirect:/grupos";
	}

	//---- PARTICIPANTES DE UN GRUPO----
	
		@RequestMapping(value = "/participantes/{idGrupo}", method = RequestMethod.GET)
		public String participantes (Model modelo, @PathVariable("idGrupo") BigDecimal idGrupo) {
			grupo = this.grupoService.findByIdGrupo(idGrupo);
			//modelo.addAttribute("datosUsuario", u);
			modelo.addAttribute("participantes", grupo.getUsuarioList());
			System.out.println("Participantes del grupo = " + grupo.getUsuarioList());
			return "participantes";
		}
		
		//---- MOSTRAR LAS PERSONAS Q SE PUEDEN ADD A UN GRUPO----
		
			@RequestMapping(value = "/mostrarParticipantesAdd", method = RequestMethod.GET)
			public String MostrarAddParticipantes (HttpServletRequest request, Model modelo) {
				session =  (SessionComponent) request.getSession().getAttribute("session");
				System.out.println(grupo.getUsuarioList());
				modelo.addAttribute("amigos", session.getUsuario().getUsuarioList());
				System.out.println("Amigos del usuario para add = " + session.getUsuario().getUsuarioList());
				return "amigos";
			}
			
		//---- ADD PARTICIPANTES GRUPO----
			
			@RequestMapping(value = "/addtogrupo/{idUsuario}", method = RequestMethod.GET)
			public String Addparticipantes (HttpServletRequest request, Model modelo, @PathVariable("idUsuario") BigDecimal idUsuario) {
				session =  (SessionComponent) request.getSession().getAttribute("session");
				modelo.addAttribute("amigos", session.getUsuario().getUsuarioList());
				Usuario u = this.userService.findById(idUsuario);
				grupo.getUsuarioList().add(u);
				this.grupoService.addGrupo(grupo);
				System.out.println("PARTICIPANTES GRUPO = " + grupo.getUsuarioList());
				String redierct ="redirect:/participantes/"+grupo.getIdGrupo();
				return redierct ;
			}
		
			
		//---- ELIMINAR PARTICIPANTES GRUPO----
			
			@RequestMapping(value = "/removetogrupo/{idUsuario}", method = RequestMethod.GET)
			public String Removeparticipantes (HttpServletRequest request, Model modelo, @PathVariable("idUsuario") BigDecimal idUsuario) {
				session =  (SessionComponent) request.getSession().getAttribute("session");
				modelo.addAttribute("amigos", session.getUsuario().getUsuarioList());
				Usuario u = this.userService.findById(idUsuario);
				grupo.getUsuarioList().remove(u);
				this.grupoService.addGrupo(grupo);
				System.out.println("PARTICIPANTES GRUPO = " + grupo.getUsuarioList());
				String redierct ="redirect:/participantes/"+grupo.getIdGrupo();
				return redierct ;
			}
	
}
	
	
	
	
	
	
