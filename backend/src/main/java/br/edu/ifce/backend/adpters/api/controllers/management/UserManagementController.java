package br.edu.ifce.backend.adpters.api.controllers.management;

import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driver.GetAUserById;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/api/v1/users")
@AllArgsConstructor
public class UserManagementController {

    private final GetAUserById getAUserById;

    @GetMapping("/{id}")
    public ResponseEntity<User> getAUserById(@PathVariable Long id) {
        var user = getAUserById.execute(id);
        return ResponseEntity.ok().body(user);
    }
}
