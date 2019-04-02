package inftel.socialweb.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ejb.access.EjbAccessException;
import org.springframework.stereotype.Service;


import inftel.socialweb.facade.UsuarioRepository;
import inftel.socialweb.model.Publicacion;
import inftel.socialweb.model.Usuario;

@Service
public class UsuarioServices {
	
	@Autowired
    UsuarioRepository usuariyrepository;

    public List<Usuario> getAllUsers() {
    	List<Usuario> usu = this.usuariyrepository.findAll();
    	for (Usuario usuario : usu) {
			for(Usuario amigo : usuario.getUsuarioList()) {
				amigo.setUsuarioList(null);
				amigo.setUsuarioList1(null);
			}
		}
    	
        return this.usuariyrepository.findAll();
    }

    public Usuario addUser(Usuario user) {
        return this.usuariyrepository.save(user);
    }
    
    public void removeUser(BigDecimal id) {
        try {
        	this.usuariyrepository.delete(id);
        	
        }catch(EjbAccessException e){
        	
        }
    }
    
    public Usuario findById(BigDecimal id) {
    	return this.usuariyrepository.findOne(id);
    }
    
    public List<BigDecimal> getIdAmigos(Usuario usuario) {
    	List<BigDecimal> idamigos = new ArrayList<BigDecimal>();
    	for(Usuario usu : usuario.getUsuarioList()) {
    		idamigos.add(usu.getIdUsuario());
    	}
    	return idamigos;
    }
    
    public void editUser(Usuario u) {
    	this.usuariyrepository.save(u);
    }
    
    public void updateUserSetFotoPerfil(String fotoperfil, BigDecimal idUsuario) {
    	this.usuariyrepository.updateUserSetFotoPerfil(fotoperfil,idUsuario);
    }
    
    public Usuario findByCorreoAndContrasena(String mail,String contrasena) { 	
    	return usuariyrepository.findByCorreoAndContrasena(mail, contrasena);
    }
    
    
    public List<Usuario> findAmigos(List<BigDecimal> usuarios) {
    	return usuariyrepository.findAmigos(usuarios);
    }
    
    public void updateAmigos(List<Usuario> amigos,BigDecimal idUsuario) { 	
    	 usuariyrepository.updateAmigos(amigos, idUsuario);
    }
    
    
    public List <Usuario> findByNombreOrApellidos(String nombre,String apellidos) { 	
    	return usuariyrepository.findByNombreOrApellidos(nombre, apellidos);
    }
}
