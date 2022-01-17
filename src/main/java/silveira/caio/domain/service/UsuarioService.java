package silveira.caio.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import silveira.caio.domain.entity.Usuario;
import silveira.caio.domain.repository.UsuarioRepository;


@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Transactional
	public Usuario salvar(Usuario usua) {
		usua.setSenha(encoder.encode(usua.getSenha()));
		return repo.save(usua);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usua = repo.findByLogin(username).orElseThrow(() -> 
			new UsernameNotFoundException("Usuário não encontrado na bsae de dados"));
		
		String[] roles = usua.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};
		
		return User.builder()
				.username(usua.getLogin())
				.password(usua.getSenha())
				.roles(roles)
				.build();
	}

	
	
}
