package beans.crud;

import entities.Playlist;
import entities.Profile;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PlaylistBean {

    @PersistenceContext(unitName = "sr-jpa")
    private EntityManager em;

    @Transactional
    @Counted(name = "PlaylistBeanCall", monotonic = true)
    public Playlist getPlaylist(int id) {
        Playlist p = em.find(Playlist.class, id);
        if (p == null) return null;
        em.refresh(p);
        return p;
    }

    @Transactional
    @Counted(name = "PlaylistBeanCall", monotonic = true)
    public List<Playlist> getPlaylists() {
        TypedQuery<Playlist> q = em.createNamedQuery("Playlist.getPlaylists", Playlist.class);
        List<Playlist> l = q.getResultList();
        em.refresh(q.getResultList());
        return l;
    }

    @Transactional
    @Counted(name = "PlaylistBeanCall", monotonic = true)
    public Playlist insertPlaylist(int profileId, Playlist playlist) {
        Profile profile = em.find(Profile.class, profileId);
        if (profile == null) return null;

        playlist.setProfile(profile);
        try {
            em.persist(playlist);
            em.flush();
            return playlist;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional
    @Counted(name = "PlaylistBeanCall", monotonic = true)
    public Playlist updatePlaylist(int id, Playlist a) {
        Playlist existing = em.find(Playlist.class, id);
        if (existing == null) {
            return null;
        }

        a.setId(existing.getId());
        a = em.merge(a);
        em.flush();
        return a;
    }

    @Transactional
    @Counted(name = "PlaylistBeanCall", monotonic = true)
    public boolean deletePlaylist(int id) {
        Playlist a = em.find(Playlist.class, id);
        if (a == null) {
            return false;
        }

        em.remove(a);
        return true;
    }

}
