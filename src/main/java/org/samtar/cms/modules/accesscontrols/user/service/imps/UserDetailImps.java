package org.samtar.cms.modules.accesscontrols.user.service.imps;


import org.samtar.cms.modules.accesscontrols.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailImps implements UserDetailsService {

 private   UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(UserPrincipleImps::new)
                .orElseThrow(()->new UsernameNotFoundException("somthing went worng"));
    }
}
