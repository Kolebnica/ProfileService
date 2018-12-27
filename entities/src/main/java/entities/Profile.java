package entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import external.User;

import javax.persistence.*;
import java.util.List;

@Entity(name = "profiles")
@NamedQueries(value = {
        @NamedQuery(name = "Profile.getProfiles", query = "SELECT p FROM profiles p")
})
public class Profile {

    @Id
    private int id;

    @OneToMany(mappedBy = "profile")
    private List<Playlist> playlists;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    // Contains details about user's profile. This transient attribute should be explicitly retrieved from UserService
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
