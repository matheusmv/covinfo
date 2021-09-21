package br.edu.ifce.security;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.ports.driven.UserAuthenticationService;
import br.edu.ifce.usecase.ports.driven.UserRepository;
import br.edu.ifce.security.jwt.JWTUtil;
import br.edu.ifce.security.user.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        try {
            var userSecurityService = (UserSecurityService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            return userRepository.findById(userSecurityService.getId());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String refreshAuthToken(String email) {
        return jwtUtil.generateToken(email);
    }
}