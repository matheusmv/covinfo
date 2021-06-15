package br.edu.ifce.backend.adpters.api.controllers;

import br.edu.ifce.backend.adpters.dto.addressdtos.FullAddressDTO;
import br.edu.ifce.backend.adpters.dto.addressdtos.UpdateAddressDTO;
import br.edu.ifce.backend.adpters.dto.messagedtos.NewMessageDTO;
import br.edu.ifce.backend.adpters.dto.userdtos.UpdateUserDTO;
import br.edu.ifce.backend.adpters.dto.userdtos.UserDTO;
import br.edu.ifce.backend.domain.ports.driver.*;
import br.edu.ifce.backend.domain.valueObjects.MedicalCareUnityInfo;
import br.edu.ifce.backend.domain.valueObjects.VaccinationData;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class AuthenticatedUserController {

    private final GetTheAuthenticatedUser getTheAuthenticatedUser;
    private final RefreshUserAuthToken refreshUserAuthToken;
    private final CreateAMessage createAMessage;
    private final UpdateAuthenticatedUserProfile updateAuthenticatedUserProfile;
    private final UpdateAuthenticatedUserAddress updateAuthenticatedUserAddress;
    private final GetDataFromTheVaccinationCampaign getDataFromTheVaccinationCampaign;
    private final ListTheMedicalCareUnitsInTheCity listTheMedicalCareUnitsInTheCity;
    private final GetFullAddressOfAuthenticatedUser getFullAddressOfAuthenticatedUser;

    @GetMapping
    public ResponseEntity<UserDTO> getTheAuthenticatedUser() {
        var user = getTheAuthenticatedUser.execute();

        return ResponseEntity.ok().body(new UserDTO(user));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshUserAuthToken(HttpServletResponse response) {
        var token = refreshUserAuthToken.execute();

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/messages")
    public ResponseEntity<Void> createAMessage(@RequestBody NewMessageDTO request) {
        createAMessage.execute(request.toMessage());

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateAuthenticatedUserProfile(@RequestBody UpdateUserDTO request) {
        updateAuthenticatedUserProfile.execute(request.toUser());

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/address")
    public ResponseEntity<Void> updateAuthenticatedUserAddress(@RequestBody UpdateAddressDTO request) {
        updateAuthenticatedUserAddress.execute(request.toAddress());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/vaccination-data")
    public ResponseEntity<VaccinationData> getDataFromTheVaccinationCampaign() {
        var data = getDataFromTheVaccinationCampaign.execute();

        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/service-units")
    public ResponseEntity<MedicalCareUnityInfo> listTheMedicalCareUnitsInTheCity() {
        var data = listTheMedicalCareUnitsInTheCity.execute();

        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/address")
    public ResponseEntity<FullAddressDTO> getFullAddressOfAuthenticatedUser() {
        var address = getFullAddressOfAuthenticatedUser.execute();

        return ResponseEntity.ok().body(new FullAddressDTO(address));
    }
}
