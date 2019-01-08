package beans.external;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import external.Song;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;
import java.util.Optional;

@ApplicationScoped
public class SongServiceBean {

    @Inject
    @DiscoverService(value = "songservice", version = "1.0.x", environment = "dev")
    private Optional<WebTarget> songServiceWebTarget;

    public Song getSong(int id) {
        if(songServiceWebTarget.isPresent()) {
            WebTarget t = songServiceWebTarget.get();
            return t.path("api/song/" + id).request().get(Song.class);
        }

        // no service found
        return null;
    }

}
