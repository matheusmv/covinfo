package br.edu.ifce.api.docs;

import br.edu.ifce.domain.User;
import br.edu.ifce.usecase.ports.dto.userdtos.SimpleUserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Api("Endpoints for administrators to obtain information about users")
public interface UserManagementControllerDocs {

    @ApiOperation(value = "Returns information about a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, we found information about a user"),
            @ApiResponse(code = 403, message = "Forbidden, only admins can access this feature"),
            @ApiResponse(code = 404, message = "Failure, we didn't find information about this user id")
    })
    ResponseEntity<User> getAUserById(
            @ApiParam(value = "user id", required = true) Long id);

    @ApiOperation(value = "Remove accounts from the system")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success, account removed from system"),
            @ApiResponse(code = 400, message = "Failure, account cannot be removed from the system"),
            @ApiResponse(code = 403, message = "Forbidden, only admins can access this feature"),
            @ApiResponse(code = 404, message = "Failure, we didn't find information about this user id")
    })
    ResponseEntity<Void> deleteAUserById(
            @ApiParam(value = "user id", required = true) Long id);

    @ApiOperation(value = "Returns all accounts registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, returns a page with user data"),
            @ApiResponse(code = 403, message = "Forbidden, only admins can access this feature")
    })
    ResponseEntity<Page<SimpleUserDTO>> listAllAccountsRegisteredInTheSystem(
            @ApiParam(value = "page: page number") Integer page,
            @ApiParam(value = "linesPerPage: number of lines per page") Integer linesPerPage,
            @ApiParam(value = "direction: ascending = ASC, descending = DESC") String direction,
            @ApiParam(value = "orderBy: User entity attributes") String orderBy);
}
