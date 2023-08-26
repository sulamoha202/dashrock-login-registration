package std.dashrock.registrationlogin.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import std.dashrock.registrationlogin.dto.UserDto;
import std.dashrock.registrationlogin.entity.Role;
import std.dashrock.registrationlogin.entity.User;
import std.dashrock.registrationlogin.repository.RoleRepository;
import std.dashrock.registrationlogin.repository.UserRepository;
import std.dashrock.registrationlogin.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public void saveUser(UserDto userDto) {
		User user = new User();
		user.setFullName(userDto.getFullName());
		user.setEmail(userDto.getEmail());
		user.setCountry(userDto.getCountry());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

		
		Role role = roleRepository.findByName("ROLE_ADMIN");
		if(role == null) {
			role = checkRoleExist();
		}
		
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
		
	}
	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}
	@Override
	public User setUser(UserDto userDto) {
		User user = new User();
		user.setFullName(userDto.getFullName());
		user.setEmail(userDto.getEmail());
		user.setCountry(userDto.getCountry());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Role role = roleRepository.findByName("ROLE_ADMIN");
		if(role == null) {
			role = checkRoleExist();
		}
		user.setRoles(Arrays.asList(role));
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findByEmailIgnoreCase(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}
	
	

	@Override
	public List<UserDto> findAllUsers() {
		List<User> users = userRepository.findAll();
		
		return users.stream()
				.map((user)->mapToUserDto(user))
				.collect(Collectors.toList());
	}
	
	private UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setFullName(userDto.getFullName());
		userDto.setEmail(user.getEmail());
		return userDto;
	}
	
	private Role checkRoleExist() {
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		return roleRepository.save(role);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if(user ==null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),getAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles){
		return roles.stream().map(role ->  new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	
}
