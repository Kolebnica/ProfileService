package api.resources;


import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("register")
@ApplicationScoped
@Tags(value = @Tag(name = "authentication"))
public class UserRegisterResource{

    @PUT
    public Response registerUser(
            @QueryParam("username") String username,
            @QueryParam("email") String email,
            @QueryParam("password") String password){

        return Response.ok().build();

    }
}
