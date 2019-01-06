package beans.external;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import external.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class UserServiceBean {

    @Inject
    @DiscoverService(value = "userservice", version = "1.0.x", environment = "dev")
    private Optional<WebTarget> profileServiceWebTarget;

    public User getUserProfile(int id) {
        // TODO get user detaild from UserService
        Logger.getLogger("KARNEKI").severe("TUKI: " + profileServiceWebTarget.toString());
        if(profileServiceWebTarget.isPresent()) {
            WebTarget t = profileServiceWebTarget.get();
            return t.path("api/users/" + id).request().get(User.class);
        }

        // no service found
        return null;
    }

}
