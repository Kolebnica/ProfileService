package beans.crud;

import entities.Playlist;
import entities.PlaylistSong;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@ApplicationScoped
public class PlaylistSongBean {

    @PersistenceContext(unitName = "sr-jpa")
    private EntityManager em;

    @Transactional
    @Counted(name = "PlaylistSongBeanCall", monotonic = true)
    public PlaylistSong getPlaylistSong(int playlistId, int songId) {
        TypedQuery<PlaylistSong> q = em.createNamedQuery("PlaylistSong.getPlaylistSong", PlaylistSong.class)
                .setParameter("playlistId", playlistId)
                .setParameter("songId", songId);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    @Counted(name = "PlaylistSongBeanCall", monotonic = true)
    public PlaylistSong insertPlaylistSong(int playlistId, int songId) {
        Playlist playlist = em.find(Playlist.class, playlistId);
        if (playlist == null) return null;

        PlaylistSong ps;
        ps = getPlaylistSong(playlistId, songId);
        if (ps != null) return ps;

        ps = new PlaylistSong();
        ps.setPlaylist(playlist);
        ps.setSongId(songId);
        try {
            em.persist(ps);
            em.flush();
            return ps;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    @Counted(name = "PlaylistSongBeanCall", monotonic = true)
    public boolean deletePlaylistSong(int playlistId, int songId) {
        TypedQuery<PlaylistSong> q = em.createNamedQuery("PlaylistSong.getPlaylistSong", PlaylistSong.class)
                .setParameter("playlistId", playlistId)
                .setParameter("songId", songId);
        try {
            PlaylistSong a = q.getSingleResult();
            em.remove(a);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

}
