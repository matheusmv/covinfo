package br.edu.ifce.backend.adpters.security.user;

import br.edu.ifce.backend.domain.ports.driven.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s not found", email)));

        return new UserSecurityService(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getLocked(),
                user.getEnabled(),
                user.getSimpleGrantedAuthorities()
        );
    }
}
