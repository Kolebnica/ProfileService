package beans.crud;

import entities.Profile;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProfileBean {

    @PersistenceContext(unitName = "sr-jpa")
    private EntityManager em;

    @Counted(name = "ProfileBeanCall")
    public Profile getProfile(int id) {
        return em.find(Profile.class, id);
    }

    @Counted(name = "ProfileBeanCall")
    public List<Profile> getProfiles() {
        TypedQuery<Profile> q = em.createNamedQuery("Profile.getProfiles", Profile.class);
        return q.getResultList();
    }

    @Transactional
    @Counted(name = "ProfileBeanCall")
    public Profile insertProfile(Profile a) {
        try {
            em.persist(a);
            em.flush();
            return a;
        } catch (EntityExistsException e) {
            return null;
        }
    }

    @Transactional
    @Counted(name = "ProfileBeanCall")
    public Profile updateProfile(int id, Profile a) {
        Profile existing = em.find(Profile.class, id);
        if (existing == null) {
            return null;
        }

        a.setId(existing.getId());
        a = em.merge(a);
        em.flush();
        return a;
    }

    @Transactional
    @Counted(name = "ProfileBeanCall")
    public boolean deleteProfile(int id) {
        Profile a = em.find(Profile.class, id);
        if (a == null) {
            return false;
        }

        em.remove(a);
        return true;
    }

}
