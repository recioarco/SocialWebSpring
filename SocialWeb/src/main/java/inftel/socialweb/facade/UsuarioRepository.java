package inftel.socialweb.facade;



import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import inftel.socialweb.model.Publicacion;
import inftel.socialweb.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, BigDecimal>{
	
	@Modifying
	@Query(value = "update Usuario u set u.nombre = ? where u.idUsuario = ?", 
	  nativeQuery = true)
	String updateUserSetNombre(String nombre, BigDecimal idUsuario);
	
	@Modifying
	@Query(value = "update Usuario u set u.apellidos = ? where u.idUsuario = ?", 
	  nativeQuery = true)
	String updateUserSetApellidos(String apellidos, BigDecimal idUsuario);
	
	@Modifying
	@Query(value = "update Usuario u set u.password = ? where u.idUsuario = ?", 
	  nativeQuery = true)
	String updateUserSetPassword(String password, BigDecimal idUsuario);
	
	@Modifying
	@Query(value = "update Usuario u set u.correo = ? where u.idUsuario = ?", 
	  nativeQuery = true)
	String updateUserSetEmail(String correo, BigDecimal idUsuario);
	
	@Modifying
	@Query(value = "update Usuario u set u.nacimiento = ? where u.idUsuario = ?", 
	  nativeQuery = true)
	String updateUserSetNacimiento(String nacimiento, BigDecimal idUsuario);
	
	
	@Modifying
	@Query(value = "update Usuario u set u.direccion = ? where u.idUsuario = ?", 
	  nativeQuery = true)
	String updateUserSetDireccion(String direccion, BigDecimal idUsuario);
	
	@Modifying
	@Query(value = "update Usuario u set u.url = ? where u.idUsuario = ?", 
	  nativeQuery = true)
	String updateUserSetUrl(String url, BigDecimal idUsuario);
	
	@Modifying
	@Query(value = "update Usuario u set u.fechaRegistro = ? where u.idUsuario = ?", 
	  nativeQuery = true)
	String updateUserSetFechaRegistro(String fechaRegistro, BigDecimal idUsuario);
	
	@Query("select u from Usuario u where u.correo = ?1 and u.password = ?2")
	Usuario findByCorreoAndContrasena(String correo, String password);
	
	
    //BUSCA USUARIOS QUE NO ESTEN EN LA LISTA
	@Query("SELECT u FROM Usuario u WHERE u.idUsuario NOT IN :amigos")
	List<Usuario> findAmigos(@Param("amigos") List<BigDecimal> amigos);
	
	@Modifying(clearAutomatically = true)
    @Transactional
	@Query(value = "UPDATE Usuario u SET u.fotoperfil = :fotoperfil WHERE u.idUsuario = :idUsuario")
	void updateUserSetFotoPerfil(@Param("fotoperfil")String fotoperfil,@Param("idUsuario") BigDecimal idUsuario);
	
	@Modifying(clearAutomatically = true)
    @Transactional
	@Query(value = "UPDATE Usuario u SET u.usuarioList = :listaAmigos WHERE u.idUsuario = :idUsuario")
	void updateAmigos(@Param("listaAmigos")List<Usuario> listaAmigos, @Param("idUsuario") BigDecimal idUsuario);
	
	//BUSCA USUARIOS DE LA LISTA DE AMIGOS
	@Query("SELECT u FROM Usuario u WHERE u.nombre = ?1 OR u.apellidos = ?2")
	List<Usuario> findByNombreOrApellidos(String nombre,String apellidos);
	
}



