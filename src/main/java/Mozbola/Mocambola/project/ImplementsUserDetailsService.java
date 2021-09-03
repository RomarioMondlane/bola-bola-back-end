/**
 * 
 */
package Mozbola.Mocambola.project;

import java.util.List;

import javax.transaction.Transactional
;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;




@Repository
 @Transactional
 public class ImplementsUserDetailsService implements UserDetailsService{

 	@Autowired
 	private ControladorUser ur;
 	
 	@Override
 	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 		Mozbola.Mocambola.project.User usuario = ur.findByUsername(username);
 		
 		if(usuario == null){
 			throw new UsernameNotFoundException("Usuario não encontrado!");
 		}
 		List<GrantedAuthority> rolesUser= AuthorityUtils.createAuthorityList("User");
		List<GrantedAuthority> rolesAdmin= AuthorityUtils.createAuthorityList("Admin");
 		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.isadmin?rolesAdmin:rolesUser);
 	}

 }
