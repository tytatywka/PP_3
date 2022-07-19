package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User userUpdate,int id) {
        User updateUser = getUserById(id);
        updateUser.setEmail(userUpdate.getEmail());
        updateUser.setFirstName(userUpdate.getFirstName());
        updateUser.setLastName(userUpdate.getLastName());
        entityManager.persist(updateUser);
    }

    @Override
    public void removeUser(int id) {
        User user = getUserById(id);
        entityManager.remove(user);
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class,id);
    }
    @Override
    @SuppressWarnings("uncheked")
    public List<User> listUser() {
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }
}
