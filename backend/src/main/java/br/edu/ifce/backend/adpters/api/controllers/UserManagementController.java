package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.userdtos.SimpleUserDTO;
import br.edu.ifce.backend.domain.entities.User;
import br.edu.ifce.backend.domain.ports.driver.DeleteAUserById;
import br.edu.ifce.backend.domain.ports.driver.GetAUserById;
import br.edu.ifce.backend.domain.ports.driver.ListAllAccountsRegisteredInTheSystem;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management/api/v1/users")
@AllArgsConstructor
public class UserManagementController {

    private final GetAUserById getAUserById;
    private final DeleteAUserById deleteAUserById;
    private final ListAllAccountsRegisteredInTheSystem listAllAccountsRegisteredInTheSystem;

    @GetMapping("/{id}")
    public ResponseEntity<User> getAUserById(@PathVariable Long id) {
        var user = getAUserById.execute(id);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAUserById(@PathVariable Long id) {
        deleteAUserById.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<SimpleUserDTO>> listAllAccountsRegisteredInTheSystem(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "createdAt") String orderBy) {

        var pageOfUsers = listAllAccountsRegisteredInTheSystem.execute(page, linesPerPage, direction, orderBy).map(SimpleUserDTO::new);

        return ResponseEntity.ok().body(pageOfUsers);
    }
}
