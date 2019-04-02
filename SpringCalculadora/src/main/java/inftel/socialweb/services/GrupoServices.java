package inftel.socialweb.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.access.EjbAccessException;
import org.springframework.stereotype.Service;

import inftel.socialweb.facade.GrupoRepository;
import inftel.socialweb.model.Grupo;

@Service
public class GrupoServices {
	
	@Autowired
    GrupoRepository gruporepository;

    public List<Grupo> getAllGrupos() {
        return this.gruporepository.findAll();
    }

    public Grupo addGrupo(Grupo grupo) {
        return this.gruporepository.save(grupo);
    }
    
    public void removeGrupo(BigDecimal id) {
        try {
        	this.gruporepository.delete(id);
        	
        }catch(EjbAccessException e){
        	
        }
    }
    
    public Grupo findByIdGrupo(BigDecimal id) {
    	return this.gruporepository.findOne(id);
    }
    
    public void editGrupo(Grupo grupo) {
    	this.gruporepository.save(grupo);
    }
    
  
}
