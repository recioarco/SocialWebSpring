package inftel.socialweb.RESTcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import inftel.socialweb.services.PublicacionServices;
import inftel.socialweb.model.Publicacion;
@RestController
@RequestMapping("/android/publicacion")
public class PublicacionREST {
	@Autowired
    PublicacionServices publicacionService;
	
	
	@GetMapping("/publicaciones")
	public List<Publicacion> findPublicacion() {
	 return publicacionService.getAllPublicaciones();	
	}

}