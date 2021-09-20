package br.edu.ifce.api.docs;

import br.edu.ifce.domain.ports.dto.CompleteZipCodeInformation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

@Api("Endpoints to get information about a zip code")
public interface ZipControllerDocs {

    @ApiOperation(value = "Returns information about a zip code")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, we found information about a zip code"),
            @ApiResponse(code = 404, message = "Failure, we didn't find information about a zip code")
    })
    ResponseEntity<CompleteZipCodeInformation> getInformationAboutZipCode(
            @ApiParam(value = "zip code: 00000-000 or 00000000", required = true) String zip);
}
