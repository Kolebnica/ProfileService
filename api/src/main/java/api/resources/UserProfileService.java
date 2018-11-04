package api.resources;

import beans.crud.UserBean;
import configurations.Configurations;
import entities.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("userprofile")
@ApplicationScoped
@Tags(value = @Tag(name = "authentication"))
public class UserProfileService {

    @Inject
    private Configurations configurations;

    @GET
    @Path("{userId}")
    public Response getUserProfile(@PathParam("userId") int userId){
        User user = new User();
        if (configurations.getShowEmail())
            user.setEmail("rendom@email.io");
        else
            user.setEmail("");
        user.setId(userId);
        user.setName("Blazka");
        user.setPassword("123456");
        user.setSurrname("Blatnik");
        user.setUsername("username");
        return Response.ok(user).build();
    }
}
