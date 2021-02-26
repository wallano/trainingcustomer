package co.com.bancolombia.clientes.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 25)
	private Long id;

	@Column(name = "username", length = 50)
	private String userName;

	@Column(name = "password", length = 800)
	private String password;

	@Column(name = "role", length = 50)
	private String role;

	@Column(name = "enabled")
	private short enabled;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public short getEnabled() {
		return enabled;
	}

	public void setEnabled(short enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return String.format("UserInfo [id=%s, userName=%s, password=%s, role=%s, enabled=%s]", id, userName, password,
				role, enabled);
	}


}
