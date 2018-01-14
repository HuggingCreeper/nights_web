package ch.jolly.nights.dao;

import ch.jolly.nights.entity.UserEntity;
import ch.jolly.nights.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserDAO {

    public static UserEntity validate(String user, String password) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery(
                "getLoginUser");
        query.setParameter("username", user);
        query.setParameter("password", password);
        List<UserEntity> list = query.getResultList();
        entityManager.close();
        try {
            return list.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    public static void addUser(UserEntity newUser) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(newUser);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static List<UserEntity> allPeople() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery(
                "from UserEntity ");
        List<UserEntity> list = query.getResultList();
        entityManager.close();
        return list;
    }
}
