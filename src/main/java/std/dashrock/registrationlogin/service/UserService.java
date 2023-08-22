package std.dashrock.registrationlogin.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import std.dashrock.registrationlogin.dto.UserDto;
import std.dashrock.registrationlogin.entity.User;

public interface UserService extends UserDetailsService {
	void saveUser(UserDto userDto);
	
	User findUserByEmail(String email);
	
	List<UserDto> findAllUsers();
}
