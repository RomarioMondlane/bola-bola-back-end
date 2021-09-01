package Mozbola.Mocambola.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	@NotBlank
	@Column(unique = true)
	private String username;
	@NotEmpty
	@JsonIgnore
	private String password;
	@NotEmpty
	public boolean isadmin;
	
	
	
	
	public User() {
	
	}
	public User(@NotBlank String username, @NotEmpty String password, @NotEmpty boolean isadmin) {
		super();
		this.username = username;
		this.password = password;
		this.isadmin = isadmin;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isIsadmin() {
		return isadmin;
	}
	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}
	@Override
	public String toString() {
		return "User [codigo=" + codigo + ", username=" + username + ", password=" + password + ", isadmin=" + isadmin
				+ "]";
	}
	
	
	
}
