package inftel.socialweb.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.access.EjbAccessException;
import org.springframework.stereotype.Service;

import inftel.socialweb.facade.PublicacionRepository;
import inftel.socialweb.model.Publicacion;
import inftel.socialweb.model.Usuario;



@Service
public class PublicacionServices {
	
	@Autowired
    PublicacionRepository publicacionrepository;

    public List<Publicacion> getAllPublicaciones() {
        return this.publicacionrepository.findAll();
    }

    public Publicacion addPublicacion(Publicacion p) {
        return this.publicacionrepository.save(p);
    }
    
    public void removePublicacion(BigDecimal id) {
        try {
        	this.publicacionrepository.delete(id);
        	
        }catch(EjbAccessException e){
        	
        }
    }
    
    public Publicacion findByIdPublicacion(BigDecimal id) {
    	return this.publicacionrepository.findOne(id);
    }
    
    public void editPublicacion(Publicacion p) {
    	this.publicacionrepository.save(p);
    }
    
    public List<Publicacion> findPublicacionesAmigos(List<Usuario> usuarios) {
    	return publicacionrepository.findPublicacionesAmigos(usuarios);
    }
    
    public void editComentario(Publicacion p) {
    	this.publicacionrepository.updateUserSetComentario(p.getComentario(),p.getIdPublicacion());
    }
    
  
}
