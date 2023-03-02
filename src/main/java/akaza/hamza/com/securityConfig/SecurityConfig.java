package akaza.hamza.com.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
		UserDetails user = User.withUsername("user").password(bCryptPasswordEncoder.encode("user")).roles("USER")
				.build();

		UserDetails admin = User.withUsername("admin").password(bCryptPasswordEncoder.encode("admin"))
				.roles("ADMIN", "USER").build();
		
		UserDetails root = User.withUsername("root").password(bCryptPasswordEncoder.encode("root"))
				.roles("ADMIN", "USER", "ROOT").build();
		return new InMemoryUserDetailsManager(admin, root, user);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/ping").permitAll()
				.requestMatchers("/api/user").hasRole("USER")
				.requestMatchers("/api/admin").hasRole("ADMIN")
				.requestMatchers("/api/root").hasRole("ROOT")
				.and()
				.formLogin().and().build();

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
