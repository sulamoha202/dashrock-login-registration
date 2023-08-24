package std.dashrock.registrationlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authorize) ->
					authorize
							.requestMatchers("/static/**", "/css/**", "/flags/**", "/fonts/**", "/images/**", "/js/**", "/plugins/**").permitAll()
						.requestMatchers("/register/**","/").permitAll()
						.requestMatchers("/auth-basic-signup").permitAll()
						.requestMatchers("/auth-basic-signin").permitAll()
						.requestMatchers("/users").hasRole("ADMIN")
						.requestMatchers("/dashboard").hasRole("ADMIN")
				).formLogin(
						form -> form
<<<<<<< HEAD
								.loginPage("/login")
								.loginProcessingUrl("/login")
=======
								.loginPage("/auth-basic-signin")
								.loginProcessingUrl("/auth-basic-signin")
>>>>>>> 53da75561645ca4cf5d1f57061b5ea15f13bacf9
								.defaultSuccessUrl("/dashboard",true)
							 	.permitAll()
						).logout(
								logout -> logout
								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.permitAll()
								);
		return http.build();
	}
	
}
