package std.dashrock.registrationlogin.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



@Entity
@Table(name="users")
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="full_name", nullable = false)
	private String fullName;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	@Column(name="password", nullable = false)
	private String password;
	@Column(name="country")
	private String country;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
			name="users_roles",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
			)
	private List<Role> roles = new ArrayList<>();
	
	@Column(name ="verficationCode")
	private String verficationCode;
	
	private boolean enabled;


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public User() {
		
	}

	public User(Long id, String full_name, String email, String password, String country, List<Role> roles) {
		this.id = id;
		this.fullName = full_name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String full_name) {
		this.fullName = full_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + fullName + ", email=" + email + ", password=" + password + ", country="
				+ country + ", roles=" + roles + "]";
	}
	
	public String getVerficationCode() {
		return verficationCode;
	}
	
	public void setVerficationCode(String verficationCode) {
		this.verficationCode = verficationCode;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
}
