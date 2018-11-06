package api.resources;

import beans.crud.UserBean;
import configurations.Configurations;
import entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import pojo.ResponseError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("userprofile")
@ApplicationScoped
@Tags(value = @Tag(name = "profile"))
public class UserProfileService {

    @Inject
    private Configurations configurations;

    @Inject
    private UserBean userBean;

    @Operation(
            summary = "User profile",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User's profile", content = @Content(schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "404", description = "User doesn't exist"),
            },
            parameters = {
                    @Parameter(name = "userId", in = ParameterIn.PATH),
            }
    )
    @GET
    @Path("{userId}")
    public Response getUserProfile(@PathParam("userId") int userId) {
        User u = userBean.getUser(userId);

        if(u == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ResponseError(404, "User doesn't exist")).build();
        }

        if (!configurations.getShowEmail())
            u.setEmail("");

        return Response.ok(u).build();
    }
}
