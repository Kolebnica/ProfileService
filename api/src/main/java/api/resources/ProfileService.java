package api.resources;

import beans.crud.ProfileBean;
import beans.external.UserServiceBean;
import configurations.Configurations;
import entities.Profile;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
                    @ApiResponse(responseCode = "200", description = "List of profiles"),
            }
    )
    @GET
    public Response getProfiles() {
        return Response.ok(profileBean.getProfiles()).build();
    }

    @Operation(
            summary = "Get profile",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Profile with given ID"),
                    @ApiResponse(responseCode = "404", description = "Profile with ID not found"),
            },
            parameters = {
                    @Parameter(name = "profileId", in = ParameterIn.PATH),
            }
    )
    @GET
    @Path("{profileId}")
    public Response getProfileById(@PathParam("profileId") int profileId) {
        Profile profile = profileBean.getProfile(profileId);
        if (profile == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        profile.setUser(userServiceBean.getUserProfile(profile.getId()));
        return Response.ok(profile).build();
    }

    //

    @Operation(
            summary = "Create profile",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Profile created"),
                    @ApiResponse(responseCode = "409", description = "Conflict when trying to create profile"),
            },
            requestBody = @RequestBody(content = {@Content(schema = @Schema(implementation = Profile.class))})
    )
    @POST
    public Response createProfile(Profile profile) {
        profile = profileBean.insertProfile(profile);
        if (profile == null) {
            return Response.status(Response.Status.CONFLICT).entity(profile).build();
        } else {
            return Response.status(Response.Status.CREATED).entity(profile).build();
        }
    }

    @Operation(
            summary = "Update profile",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Profile updated"),
                    @ApiResponse(responseCode = "404", description = "Profile with ID does not exist")
            }
    )
    @PUT
    @Path("{profileId}")
    public Response updateProfile(@PathParam("profileId") int profileId, Profile profile) {

        profile = profileBean.updateProfile(profileId, profile);

        if (profile == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).entity(profile).build();
        }
    }

    @Operation(
            summary = "Delete profile",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Profile deleted"),
                    @ApiResponse(responseCode = "404", description = "Profile with ID does not exist")
            }
    )
    @DELETE
    @Path("{profileId}")
    public Response deleteProfile(@PathParam("profileId") int profileId) {

        boolean deleted = profileBean.deleteProfile(profileId);

        if (deleted) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
