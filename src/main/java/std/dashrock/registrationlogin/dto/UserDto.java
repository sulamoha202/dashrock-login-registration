package std.dashrock.registrationlogin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


public class UserDto {
	
	private Long id;
	@NotEmpty
	private String fullName;
	@NotEmpty(message = "Email should not be empty")
	@Email
	private String email;
	@NotEmpty(message = "Password should not be empty")
	private String password;
	private String country;
	public UserDto() {
		
	}

	public UserDto(Long id, @NotEmpty String full_name,
			@NotEmpty(message = "Email should not be empty") @Email String email,
			@NotEmpty(message = "Password should not be empty") String password,String country) {
		this.id = id;
		this.fullName = full_name;
		this.email = email;
		this.password = password;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
