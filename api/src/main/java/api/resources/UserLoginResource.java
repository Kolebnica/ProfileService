package api.resources;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("login")
@ApplicationScoped
@Tags(value = @Tag(name = "authentication"))
public class UserLoginResource {

    @POST
    public Response registerUser(
            @QueryParam("username") String username,
            @QueryParam("password") String password){

        if (username.equals("user") && password.equals("123"))
            return Response.ok().build();
        else
            return Response.status(Response.Status.UNAUTHORIZED).build();

    }
}
