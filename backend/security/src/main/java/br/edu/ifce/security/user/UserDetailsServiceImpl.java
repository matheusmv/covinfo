package br.edu.ifce.security.user;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(toUserUserSecurityService)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Email %s not found", email)));
    }

    private final Function<User, UserSecurityService> toUserUserSecurityService = user -> new UserSecurityService(
            user.getId(),
            user.getEmail(),
            user.getPassword(),
            user.getLocked(),
            user.getEnabled(),
            Set.of(new SimpleGrantedAuthority(user.getRole().getRole()))
    );
}
