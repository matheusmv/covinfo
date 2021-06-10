package br.edu.ifce.backend.adpters.api.controllers.management;

import br.edu.ifce.backend.adpters.dto.addressdtos.AddressDTO;
import br.edu.ifce.backend.adpters.dto.addressdtos.FullAddressDTO;
import br.edu.ifce.backend.domain.ports.driver.GetAAddressById;
import br.edu.ifce.backend.domain.ports.driver.GetAllAddresses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/management/api/v1/addresses")
@AllArgsConstructor
public class AddressManagementController {

    private final GetAllAddresses getAllAddresses;
    private final GetAAddressById getAAddressById;

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        var listOfAddresses = getAllAddresses.execute()
                .stream()
                .map(AddressDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(listOfAddresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FullAddressDTO> find(@PathVariable Long id) {
        var address = getAAddressById.execute(id);

        return ResponseEntity.ok().body(new FullAddressDTO(address));
    }
}
