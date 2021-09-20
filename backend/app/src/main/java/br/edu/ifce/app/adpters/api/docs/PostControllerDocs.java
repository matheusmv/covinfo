package br.edu.ifce.app.adpters.api.docs;

import br.edu.ifce.domain.ports.dto.postdtos.PostDTO;
import br.edu.ifce.domain.ports.dto.postdtos.PostWithContentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api("Endpoints for posting information")
public interface PostControllerDocs {

    @ApiOperation(value = "Returns all posts in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, returns a list with posts")
    })
    ResponseEntity<List<PostDTO>> getAllPosts();


    @ApiOperation(value = "Returns information about a post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, we found information about this post"),
            @ApiResponse(code = 404, message = "Failure, we didn't find information about this post")
    })
    ResponseEntity<PostWithContentDTO> getAPostById(
            @ApiParam(value = "post id", required = true) Long id);
}
