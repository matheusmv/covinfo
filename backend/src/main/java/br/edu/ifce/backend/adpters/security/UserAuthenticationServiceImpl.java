package br.edu.ifce.backend.adpters.security;

import br.edu.ifce.backend.adpters.security.user.UserSecurityService;
import br.edu.ifce.backend.domain.ports.driven.UserAuthenticationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Override
    public UserSecurityService getAuthenticatedUser() {
        try {
            return (UserSecurityService) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
