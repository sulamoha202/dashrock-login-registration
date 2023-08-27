package std.dashrock.registrationlogin.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import std.dashrock.registrationlogin.dto.UserDto;
import std.dashrock.registrationlogin.entity.User;
import std.dashrock.registrationlogin.service.UserService;

@Controller
public class AuthController {
	
	private UserService userService;

	
	public AuthController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String home() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegisterationForm(Model model) {
		
		UserDto user = new UserDto();
		model.addAttribute("user",user);
		return "register";
	}
	
	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto,
			BindingResult result,
			Model model) {
		
		User existingUser = userService.findUserByEmail(userDto.getEmail());
		
		if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null,"there is already an account registred with the same email");
		}
		
		if(result.hasErrors()) {
			model.addAttribute("user",userDto);
			return "/register";
		}
		
		
		userService.saveUser(userDto);
		return "redirect:/register?success";
	}

	@GetMapping("/users")
	public String users(Model model) {
		List<UserDto> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@GetMapping("/login")
	public String basicSignin() {
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model,Authentication auth) {
		String email = auth.getName();
		
		User user = userService.findUserByEmail(email);
		
		model.addAttribute("user",user);
		return "dashboard";
	}
	
	
}
