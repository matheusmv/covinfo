package br.edu.ifce.api.docs;

import br.edu.ifce.usecase.ports.responses.MedicalCareUnityInfo;
import br.edu.ifce.usecase.ports.responses.VaccinationData;
import br.edu.ifce.usecase.ports.responses.FullAddressDTO;
import br.edu.ifce.usecase.ports.requests.UpdateAddressDTO;
import br.edu.ifce.usecase.ports.requests.NewMessageDTO;
import br.edu.ifce.usecase.ports.requests.UpdateUserDTO;
import br.edu.ifce.usecase.ports.responses.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

@Api("Endpoints for authenticated users get Information")
public interface AuthenticatedUserControllerDocs {

    @ApiOperation(value = "Returns information about a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, we found information about a user"),
            @ApiResponse(code = 401, message = "Unauthorized, only authenticated users can access this feature")
    })
    ResponseEntity<UserDTO> getTheAuthenticatedUser();

    @ApiOperation(value = "Update token expiration time")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success, token updated"),
            @ApiResponse(code = 401, message = "Unauthorized, only authenticated users can access this feature")
    })
    ResponseEntity<Void> refreshUserAuthToken(
            @ApiParam(value = "Authorization header", required = true) HttpServletResponse response);

    @ApiOperation(value = "Create a message")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success, message created"),
            @ApiResponse(code = 401, message = "Unauthorized, only authenticated users can access this feature"),
            @ApiResponse(code = 422, message = "Failure, missing required fields or wrong field range value")
    })
    ResponseEntity<Void> createAMessage(
            @ApiParam(value = "message content", required = true) NewMessageDTO request);

    @ApiOperation(value = "Update user profile")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success, profile updated"),
            @ApiResponse(code = 401, message = "Unauthorized, only authenticated users can access this feature"),
            @ApiResponse(code = 422, message = "Failure, missing required fields or wrong field range value")
    })
    ResponseEntity<Void> updateAuthenticatedUserProfile(
            @ApiParam(value = "user details", required = true) UpdateUserDTO request);

    @ApiOperation(value = "Update user address")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success, user address updated"),
            @ApiResponse(code = 401, message = "Unauthorized, only authenticated users can access this feature"),
            @ApiResponse(code = 422, message = "Failure, missing required fields or wrong field range value")
    })
    ResponseEntity<Void> updateAuthenticatedUserAddress(
            @ApiParam(value = "user address details", required = true) UpdateAddressDTO request);

    @ApiOperation(value = "Obtain data from the vaccination campaign in the user's city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, we found information about the vaccination campaign in the user's city"),
            @ApiResponse(code = 401, message = "Unauthorized, only authenticated users can access this feature")
    })
    ResponseEntity<VaccinationData> getDataFromTheVaccinationCampaign();

    @ApiOperation(value = "Obtain data about the health units in the user's city")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, we found information about the health units in the user's city"),
            @ApiResponse(code = 401, message = "Unauthorized, only authenticated users can access this feature")
    })
    ResponseEntity<MedicalCareUnityInfo> listTheMedicalCareUnitsInTheCity();

    @ApiOperation(value = "Returns information about user address")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, we found information about the user address"),
            @ApiResponse(code = 401, message = "Unauthorized, only authenticated users can access this feature")
    })
    ResponseEntity<FullAddressDTO> getFullAddressOfAuthenticatedUser();
}
