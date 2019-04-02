package inftel.socialweb.facade;




import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import inftel.socialweb.model.Grupo;



public interface GrupoRepository extends JpaRepository<Grupo, BigDecimal>{ 
	
	@Modifying
	@Query(value = "update Grupo u set u.nombreGrupo = ? where u.idGrupo = ?", 
	  nativeQuery = true)
	String updateUserSetNombreGrupo(String nombreGrupo, BigDecimal idGrupo);
	
	@Modifying
	@Query(value = "update Grupo u set u.fecha = ? where u.idGrupo = ?", 
	  nativeQuery = true)
	String updateUserSetFechaGrupo(String fecha, BigDecimal idGrupo);
}