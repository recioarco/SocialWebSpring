package inftel.socialweb.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ejb.access.EjbAccessException;
import org.springframework.stereotype.Service;


import inftel.socialweb.facade.UsuarioRepository;
import inftel.socialweb.model.Grupo;
import inftel.socialweb.model.Publicacion;
import inftel.socialweb.model.Usuario;

@Service
public class UsuarioServices {
	
	@Autowired
    UsuarioRepository usuariyrepository;
	
	private List<Usuario> respaldo;
	private Usuario usuarioservicio;

    public List<Usuario> getAllUsers() {
    	
    	List<Usuario> usus = this.usuariyrepository.findAll();
    	
    	for (Usuario usuario : usus) {
    		
			for(Usuario amigo : usuario.getUsuarioList()) {
				amigo.setUsuarioList(new ArrayList<Usuario>());
				amigo.setUsuarioList1(new ArrayList<Usuario>());
			}
			for(Grupo grupo: usuario.getGrupoList()) {
				grupo.setUsuarioList(new ArrayList<Usuario>());
			}
			for(Publicacion publi: usuario.getPublicacionList()) {
				publi.setUsuarioId(new Usuario());
			}	
		}
    	
        return usus;
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
    	respaldo = new ArrayList<Usuario>();
    	Usuario usuario = this.usuariyrepository.findOne(id);
    	usuarioservicio = usuario;
    	for(Usuario amigo : usuario.getUsuarioList()) {
			amigo.setUsuarioList(new ArrayList<Usuario>());
			amigo.setUsuarioList1(new ArrayList<Usuario>());
			respaldo.add(amigo);
		}
		for(Grupo grupo: usuario.getGrupoList()) {
			grupo.setUsuarioList(new ArrayList<Usuario>());
		}
		for(Publicacion publi: usuario.getPublicacionList()) {
			publi.setUsuarioId(new Usuario());
		}
		usuarioservicio.setUsuarioList(respaldo);
    	
    	return usuarioservicio;
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
