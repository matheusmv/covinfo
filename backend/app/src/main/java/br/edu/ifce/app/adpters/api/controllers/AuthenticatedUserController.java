package br.edu.ifce.app.adpters.api.controllers;

import br.edu.ifce.app.adpters.api.docs.AuthenticatedUserControllerDocs;
import br.edu.ifce.domain.ports.driver.CreateAMessage;
import br.edu.ifce.domain.ports.driver.GetDataFromTheVaccinationCampaign;
import br.edu.ifce.domain.ports.driver.GetFullAddressOfAuthenticatedUser;
import br.edu.ifce.domain.ports.driver.GetTheAuthenticatedUser;
import br.edu.ifce.domain.ports.driver.ListTheMedicalCareUnitsInTheCity;
import br.edu.ifce.domain.ports.driver.RefreshUserAuthToken;
import br.edu.ifce.domain.ports.driver.UpdateAuthenticatedUserAddress;
import br.edu.ifce.domain.ports.driver.UpdateAuthenticatedUserProfile;
import br.edu.ifce.domain.ports.dto.MedicalCareUnityInfo;
import br.edu.ifce.domain.ports.dto.VaccinationData;
import br.edu.ifce.domain.ports.dto.addressdtos.FullAddressDTO;
import br.edu.ifce.domain.ports.dto.addressdtos.UpdateAddressDTO;
import br.edu.ifce.domain.ports.dto.messagedtos.NewMessageDTO;
import br.edu.ifce.domain.ports.dto.userdtos.UpdateUserDTO;
import br.edu.ifce.domain.ports.dto.userdtos.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticatedUserController implements AuthenticatedUserControllerDocs {

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
