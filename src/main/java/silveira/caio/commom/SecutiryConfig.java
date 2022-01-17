package silveira.caio.commom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import silveira.caio.domain.service.UsuarioService;

@EnableWebSecurity
public class SecutiryConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Lazy
	private UsuarioService usuarioServ; 

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(usuarioServ)
		.passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/clientes/**").hasAnyRole("USER", "ADMIN")
		.antMatchers("/pedidos/**").hasAnyRole("USER", "ADMIN")
		.antMatchers("/produtos/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.httpBasic();
		// Para usar o login por formulário 
//		.formLogin();
	}

	
}
