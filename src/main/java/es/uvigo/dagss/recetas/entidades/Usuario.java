package es.uvigo.dagss.recetas.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  // Una tabla propia para cada subclase
@DiscriminatorColumn(name = "TIPO_USUARIO", discriminatorType = DiscriminatorType.STRING, length = 20)
public abstract class Usuario implements Serializable {

    @Id
    @TableGenerator(name = "USUARIO_GEN", table = "USUARIO_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)           
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "USUARIO_GEN")
    private Long id;

	@Column(length = 9, nullable = false)
	protected String DNI;		//DUDA: no se si hacerlo unico

	@Column(nullable = false)
	protected Nombre nombreCompleto;

	@Column(nullable = false)
	protected String email; //DUDA: no se si ponerle unico

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_USUARIO", length = 20)
    protected TipoUsuario tipo;

	@Column(nullable = false)
    private String login;

	@Column(nullable = false)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;

	private Boolean estado = true;
	
    public Usuario() {
        this.fechaAlta = Calendar.getInstance().getTime();
        this.ultimoAcceso = Calendar.getInstance().getTime();
		this.estado = true;
    }

    public Usuario(TipoUsuario tipo) {
    	this();
        this.tipo = tipo;
    }

    public Usuario(TipoUsuario tipo, String login, String password) {
    	this();
        this.tipo = tipo;
        this.login = login;
        this.password = password;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getUltimoAcceso() {
		return ultimoAcceso;
	}

	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}


	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public Boolean getActivo() {
        return estado;
    }

    public void setActivo(Boolean estado) {
        this.estado = estado;
    }

    public void activar() {
        this.estado = true;
    }

    public void desactivar() {
        this.estado = false;
    }

	@Override
	public int hashCode() {
		if (this.id !=null)
			return Objects.hash(id);
		return Objects.hash(fechaAlta, login);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (this.id !=null)
			return this.id.equals(other.getId());
		return Objects.equals(fechaAlta, other.fechaAlta)
				&& Objects.equals(login, other.login);
	}
}
