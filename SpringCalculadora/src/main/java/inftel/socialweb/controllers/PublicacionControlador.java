package inftel.socialweb.controllers;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import inftel.socialweb.googleservices.GoogleServices;
import inftel.socialweb.model.Publicacion;
import inftel.socialweb.model.Usuario;
import inftel.socialweb.services.PublicacionServices;


@Controller
public class PublicacionControlador {
	
	@Autowired
	PublicacionServices publicacionService;
	
	@Autowired
	SessionComponent session;
	
	BigDecimal id;
	
	// ----- LISTAR PUBLICACIONES -----
	
	@RequestMapping(value = "/publicaciones", method = RequestMethod.GET)
	public String listarPublicaciones (Model modelo) {
		modelo.addAttribute("publicaciones", this.publicacionService.getAllPublicaciones());
		return "listarPublicaciones";
	}
	
	// ----- CREAR PUBLICACION -----
	
	@RequestMapping(value = "/nuevaPublicacion", method = RequestMethod.GET)
	public String comentario (HttpServletRequest request, Model modelo) {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session == null || session.isEmpty()) {
			return "inicio";
		}
		modelo.addAttribute("publicacion", new Publicacion());
		return "comentario";
	}
	
	// ----- SUBIR PUBLICACION -----
	
	@RequestMapping(value = "/UpPublicacion", method = RequestMethod.POST)
	public String comentario (HttpServletRequest request, @ModelAttribute("publicacion") Publicacion publi, @RequestParam("uploadfile") MultipartFile uploadfile, @RequestParam("comentario") String comentario) throws Exception {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session == null || session.isEmpty()) {
			return "inicio";
		}
		publi.setComentario(comentario);
		publi.setUsuarioId(session.getUsuario());
		publi.setFechaPublicacion(new Date());
		com.google.api.services.drive.model.File googleFile = GoogleServices.createGoogleFile(session.getUsuario().getUrl(), session.getUsuario().getIdUsuario() + " PUBLI" , uploadfile);
		publi.setUrl(googleFile.getWebContentLink());
		session.getUsuario().getPublicacionList().add(publi);
		//modelo.addAttribute("datosUsuario", u);
		publicacionService.addPublicacion(publi);
		return "redirect:/principal";
	}
	
	// ----- BORRAR PUBLICACION -----

	
	@RequestMapping(value = "/borrarPublicacion/{idPublicacion}", method = RequestMethod.GET)
	public String borrarPublicacion (HttpServletRequest request, Model modelo, @PathVariable("idPublicacion") BigDecimal idPublicacion) {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session == null || session.isEmpty()) {
			return "inicio";
		}
		session.getUsuario().getPublicacionList().remove(idPublicacion);
		this.publicacionService.removePublicacion(idPublicacion);
		return "redirect:/principal";
	}
	
	// ----- EDITAR PUBLICACION -----
	

	@RequestMapping(value = "/editarP/{idPublicacion}", method = RequestMethod.GET)
	public String editarP (HttpServletRequest request, Model modelo,@PathVariable("idPublicacion") BigDecimal idPublicacion) {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session == null || session.isEmpty()) {
			return "inicio";
		}
		id=idPublicacion;
		return "editar_publicacion";
	}
	
	@RequestMapping(value = "/editarPublicacion", method = RequestMethod.POST)
	public String editarComentario (HttpServletRequest request, @ModelAttribute("publicacion") Publicacion publi, @RequestParam("comentario") String comentario) {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session == null || session.isEmpty()) {
			return "inicio";
		}
		publi = this.publicacionService.findByIdPublicacion(id);
		System.out.println(publi.getIdPublicacion());
		publi.setComentario(comentario);
		this.publicacionService.editComentario(publi);
		return "redirect:/publicaciones";
	}

	
	

	
	

}
