package main;

import ch.jolly.nights.CommonMethod;
import ch.jolly.nights.entity.EventEntity;
import ch.jolly.nights.entity.ParticipantEntity;
import ch.jolly.nights.entity.StatusEntity;
import ch.jolly.nights.entity.UserEntity;
import ch.jolly.nights.handler.PasswordHandler;
import ch.jolly.nights.util.JPAUtil;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class TestPersistence {

    final static Logger logger = Logger.getLogger(TestPersistence.class);


    public void editEventStatus() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        EventEntity event = entityManager.find(EventEntity.class, 10);

        for (ParticipantEntity part : event.getInvited()) {
            part.setStatus(entityManager.find(StatusEntity.class, 3));
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Test
    public void getUser() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery(
                "from UserEntity ");
        List<UserEntity> list = query.getResultList();
        for (UserEntity u : list) {
            System.out.println("StatusEntity ID :" + u.getId());
            System.out.println("\tFirstname :" + u.getFirstname());
            System.out.println("\tLastname :" + u.getLastname());
            System.out.println("\tUsername :" + u.getUsername());
            System.out.println("\tAddress :" + u.getAddress().getStreet());
            System.out.println("\tPlace :" + u.getPlace().getPlace());
        }
        entityManager.close();
    }

    @Test
    public void getLoginuser() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery(
                "getLoginUser");
        query.setParameter("username", "java");
        query.setParameter("password", PasswordHandler.hashIt("12348"));
        List<UserEntity> list = query.getResultList();
        for (UserEntity u : list) {
            System.out.println("StatusEntity ID :" + u.getId());
            System.out.println("\tFirstname :" + u.getFirstname());
            System.out.println("\tLastname :" + u.getLastname());
            System.out.println("\tUsername :" + u.getUsername());
            System.out.println("\tAddress :" + u.getAddress().getStreet());
            System.out.println("\tPlace :" + u.getPlace().getPlace());
        }
        entityManager.close();
    }

    @Test
    public void saveUser() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        UserEntity userEntity = new UserEntity("ga@gmail.com", "goy", PasswordHandler.hashIt("fa"), "joshua", "kern",
                CommonMethod.stringToDate("19.02.1999"));
        entityManager.getTransaction().begin();
        entityManager.persist(userEntity);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void saveInvites() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        EventEntity e = new EventEntity("Test", CommonMethod.convertTimestamp("21.12.2017", "20:00"),CommonMethod.convertTimestamp("22.12.2017", "20:00"), "Test", 0, 8.366808, 47.442652);
        entityManager.persist(e);

        ParticipantEntity p = new ParticipantEntity(e, entityManager.find(UserEntity.class, 8), entityManager.find(StatusEntity.class, 1));
        entityManager.persist(p);

        e.getInvited().add(p);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void getInvites() {
        logger.debug("Boy");
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createQuery(
                "from EventEntity ");
        List<EventEntity> list = query.getResultList();

        for (EventEntity e : list) {
            System.out.println("*---------------*");
            System.out.println(e.getName());

            for (ParticipantEntity p : e.getInvited()) {
                System.out.println(p.getUser().getEmail());
                System.out.println(p.getStatus().getType());
                System.out.println("---");
            }

        }

        entityManager.close();
    }
}
