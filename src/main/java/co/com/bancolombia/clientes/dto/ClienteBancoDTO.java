package co.com.bancolombia.clientes.dto;

public class ClienteBancoDTO {
	
	private Long id;
	private String cedula;
	private String primerNombre;
	private String primerApellido;
	private CuentaDTO cuentaDTO;

	public ClienteBancoDTO() {
	}

	public ClienteBancoDTO(Long id, String cedula, String primerNombre, String primerApellido, CuentaDTO cuentaDTO) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.cuentaDTO = cuentaDTO;
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

	public CuentaDTO getCuentaDTO() {
		return cuentaDTO;
	}

	public void setCuentaDTO(CuentaDTO cuentaDTO) {
		this.cuentaDTO = cuentaDTO;
	}
	
	

}
