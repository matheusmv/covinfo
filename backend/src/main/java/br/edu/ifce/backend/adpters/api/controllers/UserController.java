package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.db.jpa.UserJpaRepository;
import br.edu.ifce.backend.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping
    public ResponseEntity<?> listAll() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody User user) {
        return null;
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
