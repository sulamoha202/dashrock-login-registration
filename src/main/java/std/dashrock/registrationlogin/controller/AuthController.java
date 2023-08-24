package std.dashrock.registrationlogin.controller;

import java.util.List;

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
		return "index";
	}
<<<<<<< HEAD
=======
	
	@GetMapping("/custom_login")
	public String login( ) {
		return "custom_login";
	}
>>>>>>> 53da75561645ca4cf5d1f57061b5ea15f13bacf9

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

<<<<<<< HEAD
	@GetMapping("/login")
=======
	@GetMapping("/auth-basic-signup")
	public String showBasicSignup(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user",user);

		return "auth-basic-signup";
	}
	@PostMapping("/auth-basic-signup/save")
	public String basicSignup(@Valid @ModelAttribute("user") UserDto userDto,
							  BindingResult result,
							  Model model) {

		User existingUser = userService.findUserByEmail(userDto.getEmail());

		if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null,"there is already an account registred with the same email");
		}

		if(result.hasErrors()) {
			model.addAttribute("user",userDto);
			return "auth-basic-signup";
		}


		userService.saveUser(userDto);
		return "redirect:/auth-basic-signup?success";
	}
	@GetMapping("/auth-basic-signin")
>>>>>>> 53da75561645ca4cf5d1f57061b5ea15f13bacf9
	public String basicSignin() {
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
	
}
