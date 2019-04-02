package inftel.socialweb.RESTcontrollers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import inftel.socialweb.model.Usuario;
import inftel.socialweb.services.UsuarioServices;

@RestController
@RequestMapping("/android/usuario")
public class UsuarioREST {
	
	@Autowired
    UsuarioServices usuarioService;
	
	
	@GetMapping("/users")
	public List<Usuario> findUsuario() {
	 return usuarioService.getAllUsers();	
	}
	
	@GetMapping("/users/{id}")
	public Usuario findUsuario(@PathVariable BigDecimal id) {
	 return usuarioService.findById(id);
	}

}
