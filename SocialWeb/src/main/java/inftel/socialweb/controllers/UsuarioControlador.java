package inftel.socialweb.controllers;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import inftel.socialweb.googleservices.GoogleServices;
import inftel.socialweb.model.Usuario;
import inftel.socialweb.services.UsuarioServices;

@Controller
public class UsuarioControlador {
	
	@Autowired
    UsuarioServices userService;
	
	@Autowired
	SessionComponent session;
	
	@Autowired
	SessionComponent amigo;
	
	Usuario usuario;
	
	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
	
	// ----- SUBIR FOTO -----
	
	@RequestMapping(value = "/upfotoperfil", method = RequestMethod.POST)
	public String upfotoperfil (HttpServletRequest request, @RequestParam("uploadfile") MultipartFile uploadfile) throws Exception {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session.isEmpty()) {
			return "inicio";
		}
		com.google.api.services.drive.model.File googleFile = GoogleServices.createGoogleFile(session.getUsuario().getUrl(), session.getUsuario().getNombre() + "_PERFIL" , uploadfile);
		session.getUsuario().setFotoperfil(googleFile.getWebContentLink());
		request.getSession().setAttribute("session",session);
		this.userService.updateUserSetFotoPerfil(googleFile.getWebContentLink(),session.getUsuario().getIdUsuario());
		return "redirect:/perfil";
	}
	
	// ----- AÑADIR NUEVOS AMIGOS  -----

	@RequestMapping(value = "/buscaramigos", method = RequestMethod.GET)
	public String buscarAmigos (HttpServletRequest request, Model modelo) {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session.isEmpty()) {
			return "inicio";
		}
		List<BigDecimal> idamigos = userService.getIdAmigos(session.getUsuario());
		System.out.println("Amigos del usuario = " + userService.findAmigos(idamigos));
		modelo.addAttribute("amigos", userService.findAmigos(idamigos));
		modelo.addAttribute("session",session);
		return "amigos";
	}
	
	// ----- AMIGOS DEL USUARIO -----

	@RequestMapping(value = "/amigos", method = RequestMethod.GET)
	public String amigos (HttpServletRequest request, Model modelo) {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session.isEmpty()) {
			return "inicio";
		}
		modelo.addAttribute("amigos", session.getUsuario().getUsuarioList());
		modelo.addAttribute("session",session);
		System.out.println("Amigos del usuario = " + session.getUsuario().getUsuarioList());
		return "amigos";
	}
	
	// ----- ELIMINAR USUARIO -----

	@RequestMapping(value = "/borrar", method = RequestMethod.GET)
	public String borrar (Model modelo, @PathVariable("idUsuario") BigDecimal idUsuario) {
		this.userService.removeUser(idUsuario);
		return "redirect:/";
	}
	
//  ---- ADD AMIGO----
	
	@RequestMapping(value = "/addtoamigos/{idUsuario}", method = RequestMethod.GET)
	public String AddAmigos (HttpServletRequest request, Model modelo, @PathVariable("idUsuario") BigDecimal idUsuario) {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		Usuario u = this.userService.findById(idUsuario);
		System.out.println("AMIGOS ANTIGUOS"+ session.getUsuario().getUsuarioList());
		session.getUsuario().getUsuarioList().add(u);
		System.out.println("NUEVOS AMIGOS"+ session.getUsuario().getUsuarioList());
		this.userService.editUser(session.getUsuario());
		return "redirect:/amigos";
	}	
			
	//    ---- REMOVE AMIGO----
			
	@RequestMapping(value = "/eliminartoamigos/{idUsuario}", method = RequestMethod.GET)
	public String RemoveAmigos (HttpServletRequest request, Model modelo, @PathVariable("idUsuario") BigDecimal idUsuario) {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		Usuario u = this.userService.findById(idUsuario);
		System.out.println("AMIGOS ANTIGUOS"+ session.getUsuario().getUsuarioList());
		session.getUsuario().getUsuarioList().remove(u);
		System.out.println("NUEVOS AMIGOS"+ session.getUsuario().getUsuarioList());
		this.userService.editUser(session.getUsuario());
		return "redirect:/amigos";
	}
	
	// ----- EDITAR USUARIO -----
	
	@RequestMapping(value = "/editarUsuario", method = RequestMethod.GET)
	public String editar (HttpServletRequest request, Model modelo) {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session.isEmpty()) {
			return "inicio";
		}
		modelo.addAttribute("datosUsuario", session.getUsuario());
		modelo.addAttribute("session",session);
		return "editarUsuario";
	}
	
	@RequestMapping(value = "/guardaredicion", method = RequestMethod.POST)
	public String GuardarEdicionUsu (HttpServletRequest request,@RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("correo") String correo, @RequestParam("password") String password, @RequestParam("nacimiento") String nacimiento,  @RequestParam("direccion") String direccion, @RequestParam("sexo") String sexo) throws Exception {
		session =  (SessionComponent) request.getSession().getAttribute("session");
		if(session.isEmpty()) {
			return "inicio";
		}
		
		session.getUsuario().setNombre(nombre);	
		session.getUsuario().setApellidos(apellidos);
		session.getUsuario().setCorreo(correo);
		session.getUsuario().setPassword(password);
		session.getUsuario().setDireccion(direccion);
		session.getUsuario().setSexo(sexo);
		String sDate1= nacimiento;  
	    Date date1=dateFormat.parse(sDate1);
	    session.getUsuario().setNacimiento(date1);
		String sdate2 = dateFormat.format(new Date());
		Date date2 = dateFormat.parse(sdate2);
		session.getUsuario().setFechaRegistro(date2);

		this.userService.editUser(session.getUsuario());
		return "redirect:/perfil";
	}
	
	
	// ----- REGISTRAR USUARIO -----
	
	@RequestMapping(value = "/registrar", method = RequestMethod.GET)
	public String nuevo (Model modelo) {
		modelo.addAttribute("datosUsuario", new Usuario());
		return "registrar";
	}

	
	
	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public String registrarUsu (@RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("correo") String correo, @RequestParam("password") String password, @RequestParam("nacimiento") String nacimiento,  @RequestParam("direccion") String direccion, @RequestParam("sexo") String sexo) throws Exception {
		Usuario u = new Usuario();
		u.setNombre(nombre);	
		u.setApellidos(apellidos);
		u.setCorreo(correo);
		u.setPassword(password);
		u.setDireccion(direccion);
		u.setUrl("_");
		u.setSexo(sexo);
		String sDate1= nacimiento;  
	    Date date1=dateFormat.parse(sDate1);
		u.setNacimiento(date1);
		String sdate2 = dateFormat.format(new Date());
		Date date2 = dateFormat.parse(sdate2);
	    u.setFechaRegistro(date2);
		this.userService.addUser(u);
		System.out.println("ESTO ES EL ID " + u.getIdUsuario());
		com.google.api.services.drive.model.File googleFile = GoogleServices.createGoogleFolder("1GjBoFOsnE-mVxqQTQbJRhSf-U7094qIX",Integer.toString(u.getIdUsuario().intValue()));
		u.setUrl(googleFile.getId());
		this.userService.editUser(u);
		return "redirect:/";
	}
	
	// ----- BUSCAR AMIGO -----
	
	@RequestMapping(value = "/buscar", method = RequestMethod.POST)
	public String BuscarAmigos (HttpServletRequest request, Model modelo, @RequestParam("busqueda") String sbusqueda) {
		StringTokenizer tokens=new StringTokenizer(sbusqueda);
		List<SessionComponent> listaAddAmigos = new ArrayList<SessionComponent>();
		session =  (SessionComponent) request.getSession().getAttribute("session");
		List<Usuario> busqueda = this.userService.findByNombreOrApellidos(tokens.nextToken(),tokens.nextToken());
		//List<Usuario> busqueda = this.userService.findByNombre("MARINA");
		System.out.println("GENTE BUSCADA" +busqueda);
		for(Usuario bus: busqueda){
			for(Usuario ami: session.getUsuario().getUsuarioList()) {
				amigo = new SessionComponent();
				if(session.getUsuario().getUsuarioList().contains(bus)) {
					amigo.setValidate(true);
					//BOTON VER PERFIL
					//BOTON ELIMINAR AMIGO
				}
				
				else {
					amigo.setValidate(false);
					//BOTON ADD AMIGO
				}
			}
			amigo.setUsuario(bus); 
			listaAddAmigos.add(amigo);
		}
		modelo.addAttribute("buscada",listaAddAmigos );
		return "amigos2";
	}
		
}
