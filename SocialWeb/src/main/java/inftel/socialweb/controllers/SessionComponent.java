package inftel.socialweb.controllers;


import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import inftel.socialweb.model.Usuario;


@Component
public class SessionComponent {
	
	private  Usuario usuario;
	
	private Boolean validate;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getValidate() {
		return validate;
	}
	
	public void setValidate(Boolean validate) {
		this.validate = validate;
	}
	
	public Boolean isEmpty() {
		if( !validate || usuario != null ) {
			return false;
		}else{
		    return true;	
		}	
	}
	
	
	
	
}