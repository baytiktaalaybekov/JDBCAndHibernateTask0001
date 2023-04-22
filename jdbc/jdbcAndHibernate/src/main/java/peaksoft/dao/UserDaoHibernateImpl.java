package peaksoft.dao;

import jakarta.persistence.EntityManager;
import peaksoft.config.DatabaseConf;
import peaksoft.model.User;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao,AutoCloseable {

    private final EntityManager entityManager = DatabaseConf.getEntityManager();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("drop table users").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        entityManager.getTransaction().begin();
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public void removeUserById(long id) {
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        entityManager.remove(User);
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public List<User> getAllUsers() {
        entityManager.getTransaction().begin();
        List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
         entityManager.getTransaction().begin();
         entityManager.createQuery("delete from User ").executeUpdate();
         entityManager.getTransaction().commit();
         entityManager.close();



    }

    @Override
    public void close() throws Exception {
        entityManager.close();
    }
}
