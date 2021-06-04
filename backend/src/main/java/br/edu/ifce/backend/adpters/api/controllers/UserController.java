package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.db.jpa.UserJpaRepository;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driver.RegisterAUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final RegisterAUser registerAUser;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody User user) {
        var newUser = registerAUser.execute(user);
        return new ResponseEntity<String>(newUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return null;
    }
}
