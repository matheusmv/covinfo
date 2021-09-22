package br.edu.ifce.api.controllers;

import br.edu.ifce.api.docs.UserManagementControllerDocs;
import br.edu.ifce.usecase.ports.driver.DeleteAUserById;
import br.edu.ifce.usecase.ports.driver.GetAUserById;
import br.edu.ifce.usecase.ports.driver.ListAllAccountsRegisteredInTheSystem;
import br.edu.ifce.usecase.ports.responses.SimpleUserDTO;
import br.edu.ifce.usecase.ports.responses.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/management/api/v1/users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserManagementController implements UserManagementControllerDocs {

    private final GetAUserById getAUserById;
    private final DeleteAUserById deleteAUserById;
    private final ListAllAccountsRegisteredInTheSystem listAllAccountsRegisteredInTheSystem;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getAUserById(@PathVariable Long id) {
        var user = getAUserById.execute(id);

        return ResponseEntity.ok().body(new UserDTO(user));
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
