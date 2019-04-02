package inftel.socialweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author beatrizillanesalcaide
 */
@Entity
@Table(name = "PUBLICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p")
    , @NamedQuery(name = "Publicacion.findByIdPublicacion", query = "SELECT p FROM Publicacion p WHERE p.idPublicacion = :idPublicacion")
    , @NamedQuery(name = "Publicacion.findByFechaPublicacion", query = "SELECT p FROM Publicacion p WHERE p.fechaPublicacion = :fechaPublicacion")
    , @NamedQuery(name = "Publicacion.findByComentario", query = "SELECT p FROM Publicacion p WHERE p.comentario = :comentario")})
public class Publicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SEQPUBLI", sequenceName = "SEQPUBLI", initialValue = 4, allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQPUBLI")
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PUBLICACION")
    private BigDecimal idPublicacion;
    @Column(name = "FECHA_PUBLICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPublicacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "COMENTARIO")
    private String comentario;
    @Size(max = 1000)
    @Column(name = "URL")
    private String url;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID_USUARIO")
    @ManyToOne(optional = false)
    private Usuario usuarioId;

    public Publicacion() {
    }

    public Publicacion(BigDecimal idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Publicacion(BigDecimal idPublicacion, String comentario) {
        this.idPublicacion = idPublicacion;
        this.comentario = comentario;
    }

    public BigDecimal getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(BigDecimal idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPublicacion != null ? idPublicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion) object;
        if ((this.idPublicacion == null && other.idPublicacion != null) || (this.idPublicacion != null && !this.idPublicacion.equals(other.idPublicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "proyecto.entity.Publicacion[ idPublicacion=" + idPublicacion + " ]";
    }
    
}
