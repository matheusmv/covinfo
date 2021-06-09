package br.edu.ifce.backend.adpters.security;

import br.edu.ifce.backend.adpters.security.jwt.JWTUtil;
import br.edu.ifce.backend.adpters.security.user.UserSecurityService;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public UserSecurityService getAuthenticatedUser() {
        try {
            return (UserSecurityService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String refreshAuthToken(String email) {
        return jwtUtil.generateToken(email);
    }
}
