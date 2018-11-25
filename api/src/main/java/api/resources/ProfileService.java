package api.resources;

import beans.crud.ProfileBean;
import beans.external.UserServiceBean;
import configurations.Configurations;
import entities.Profile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("profiles")
@ApplicationScoped
@Tags(value = @Tag(name = "profiles"))
public class ProfileService {

    @Inject
    private ProfileBean profileBean;
    @Inject
    private UserServiceBean userServiceBean;

    @Operation(
            summary = "Get profiles",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of user profiles"),
            }
    )
    @GET
    public Response getProfiles() {
        return Response.ok(profileBean.getAllProfiles()).build();
    }

    @Operation(
            summary = "User login",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Profile with given profileId."),
            },
            parameters = {
                    @Parameter(name = "profileId", in = ParameterIn.PATH),
            }
    )
    @GET
    @Path("{profileId}")
    public Response getProfileById(@PathParam("profileId") int profileId) {

        Profile p = profileBean.getProfile(profileId);
        p.setUser(userServiceBean.getUserProfile(profileId));

        return Response.ok(p).build();
    }

    // TODO: POST, PUT for profiles (profile editing)
    // TODO: GET, POST, PUT for playlists  (playlist by id view and playlist editing)

}
