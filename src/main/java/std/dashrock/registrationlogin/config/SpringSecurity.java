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
					authorize.requestMatchers("/register/**").permitAll()
						.requestMatchers("/index").permitAll()
						.requestMatchers("/users").hasRole("ADMIN")
				).formLogin(
						form -> form
								.loginPage("/api/custom_login")
								.loginProcessingUrl("/api/custom_login")
								.successForwardUrl("/api/users")
								
							 	.permitAll()
						).logout(
								logout -> logout
								.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.permitAll()
								);
		return http.build();
	}
	
}
