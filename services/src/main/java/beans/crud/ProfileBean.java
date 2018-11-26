package beans.crud;

import entities.Profile;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProfileBean {

    @PersistenceContext(unitName = "sr-jpa")
    private EntityManager em;

    @Transactional
    public List<Profile> getAllProfiles() {
        TypedQuery<Profile> q = em.createNamedQuery("Profile.getProfiles", Profile.class);
        return q.getResultList();
    }

    @Transactional
    public Profile getProfile(int id) {
        return em.find(Profile.class, id);
    }

    @Transactional
    public Profile insertProfile(Profile p) {
        em.persist(p);
        em.flush();
        return p;
    }

    @Transactional
    public Profile updateProfile(Profile p) {
        em.merge(p);
        em.flush();
        return p;
    }

    public void deleteProfile(int id) {
        // TODO
    }

}
