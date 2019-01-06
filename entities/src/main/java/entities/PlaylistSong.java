package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import external.Song;

import javax.persistence.*;

@Entity(name = "playlists_songs")
public class PlaylistSong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "song_id")
    private int songId;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Song song;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    // @JsonIgnoreProperties({"playlistSongs"})
    @JsonIgnore
    private Playlist playlist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
