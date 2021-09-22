package br.edu.ifce.api.docs;

import br.edu.ifce.usecase.ports.requests.EmailDTO;
import br.edu.ifce.usecase.ports.requests.PasswordDTO;
import br.edu.ifce.usecase.ports.requests.UserRegistrationDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api("Endpoints for users to create, confirm and retrieve accounts")
public interface UserControllerDocs {

    @ApiOperation(value = "Create a new account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, registered account"),
            @ApiResponse(code = 400, message = "Failure, invalid attributes"),
            @ApiResponse(code = 422, message = "Failure, missing required fields or wrong field range value")
    })
    ResponseEntity<Void> registerAUser(
            @ApiParam(value = "user information", required = true) UserRegistrationDTO request);

    @ApiOperation(value = "Confirm a new account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, account has been confirmed"),
            @ApiResponse(code = 400, message = "Failure, invalid token")
    })
    ResponseEntity<String> confirmNewUserAccount(
            @ApiParam(value = "token sent by email", required = true) String token);

    @ApiOperation(value = "Resend Account Confirmation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, a new activation token has been sent"),
            @ApiResponse(code = 400, message = "Failure, account has already been activated"),
            @ApiResponse(code = 404, message = "Failure, account email is invalid")
    })
    ResponseEntity<String> resendEmailConfirmation(
            @ApiParam(value = "account's e-mail", required = true) EmailDTO request);

    @ApiOperation(value = "Endpoint to send password recovery")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success, a new password reset token has been sent"),
            @ApiResponse(code = 400, message = "Failure, account email is invalid")
    })
    ResponseEntity<Void> sendLinkToChangePassword(
            @ApiParam(value = "account's e-mail", required = true) EmailDTO request);

    @ApiOperation(value = "Endpoint to reset password")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success, a new password reset token has been sent"),
            @ApiResponse(code = 400, message = "Failure, invalid token"),
            @ApiResponse(code = 422, message = "Failure, missing required fields or wrong field range value")
    })
    ResponseEntity<Void> resetUserPassword(
            @ApiParam(value = "token sent by email", required = true) String token,
            @ApiParam(value = "new password", required = true) PasswordDTO request);
}
