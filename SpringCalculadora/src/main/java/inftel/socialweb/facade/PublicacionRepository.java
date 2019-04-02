package inftel.socialweb.facade;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import inftel.socialweb.model.Publicacion;
import inftel.socialweb.model.Usuario;


public interface PublicacionRepository extends JpaRepository<Publicacion, BigDecimal>{ 
	
	
	@Query("SELECT p FROM Publicacion p WHERE p.usuarioId IN :amigos ORDER BY p.fechaPublicacion DESC")
	List<Publicacion> findPublicacionesAmigos(@Param("amigos") List<Usuario> amigos);
	
	@Modifying(clearAutomatically = true)
    @Transactional
	@Query("update Publicacion p set p.comentario =:comentario where p.idPublicacion =:idPublicacion")
	void updateUserSetComentario(@Param("comentario") String comentario, @Param("idPublicacion") BigDecimal idPublicacion);
	

	
	
	
}