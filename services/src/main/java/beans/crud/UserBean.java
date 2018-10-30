package beans.crud;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import entities.User;

@ApplicationScoped
public class UserBean {

    @PersistenceContext(unitName = "sr-jpa")
    private EntityManager em;

    @Transactional
    public User setUser(User user) {
        return null;
    }

    public User getUser(int userId){
        User user = new User();
        user.setEmail("rendom@email.io");
        user.setId(userId);
        user.setName("Blazka");
        user.setPassword("123456");
        user.setSurrname("Blatnik");
        user.setUsername("username123");

        return user;
    }
}
