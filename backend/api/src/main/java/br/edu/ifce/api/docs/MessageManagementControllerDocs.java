package br.edu.ifce.api.docs;

import br.edu.ifce.domain.ports.dto.messagedtos.MessageDTO;
import br.edu.ifce.domain.ports.dto.messagedtos.MessageWithContentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api("Endpoints for message information")
public interface MessageManagementControllerDocs {

    @ApiOperation(value = "Returns all messages in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, returns a list with messages"),
            @ApiResponse(code = 403, message = "Forbidden, only admins can access this feature")
    })
    ResponseEntity<List<MessageDTO>> getAllMessages();

    @ApiOperation(value = "Returns information about a message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, we found information about this message"),
            @ApiResponse(code = 403, message = "Forbidden, only admins can access this feature"),
            @ApiResponse(code = 404, message = "Failure, we didn't find information about this message")
    })
    ResponseEntity<MessageWithContentDTO> getAMessageById(
            @ApiParam(value = "message id", required = true) Long id);
}
