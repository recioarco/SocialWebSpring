package inftel.socialweb.RESTcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import inftel.socialweb.services.PublicacionServices;
import inftel.socialweb.model.Publicacion;

public class PublicacionREST {
	@Autowired
    PublicacionServices publicacionService;
	
	
	@GetMapping("/publicaciones")
	public List<Publicacion> findPublicacion() {
	 return publicacionService.getAllPublicaciones();	
	}

}