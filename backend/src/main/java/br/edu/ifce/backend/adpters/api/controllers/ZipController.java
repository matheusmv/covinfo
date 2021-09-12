package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.api.docs.ZipControllerDocs;
import br.edu.ifce.backend.domain.ports.driver.GetInformationAboutZipCode;
import br.edu.ifce.backend.domain.valueObjects.CompleteZipCodeInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/zip")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ZipController implements ZipControllerDocs {

    private final GetInformationAboutZipCode getInformationAboutZipCode;

    @GetMapping("/{zip}")
    public ResponseEntity<CompleteZipCodeInformation> getInformationAboutZipCode(@PathVariable String zip) {
        var completeInformation = getInformationAboutZipCode.execute(zip);

        return ResponseEntity.ok().body(completeInformation);
    }
}
