package co.com.bancolombia.clientes.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ClienteBanco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cedula;
	private String primerNombre;
	private String primerApellido;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ct_id", referencedColumnName = "id")
	private Cuenta cuenta;
	public ClienteBanco() {
		super();
	}
	public ClienteBanco(Long id, String cedula, String primerNombre, String primerApellido, Cuenta cuenta) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.cuenta = cuenta;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	

}
