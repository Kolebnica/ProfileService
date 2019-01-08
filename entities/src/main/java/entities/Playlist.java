package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity(name = "playlists")
@NamedQueries(value = {
        @NamedQuery(name = "Playlist.getPlaylists", query = "SELECT p FROM playlists p")
})
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    @JsonIgnoreProperties({"playlists"})
    private Profile profile;

    private String title;

    private String description;

    private Timestamp created;

    private Timestamp updated;

    @OneToMany(mappedBy = "playlist", cascade = {CascadeType.REMOVE})
    private List<PlaylistSong> playlistSongs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public List<PlaylistSong> getPlaylistSongs() {
        return playlistSongs;
    }
}
