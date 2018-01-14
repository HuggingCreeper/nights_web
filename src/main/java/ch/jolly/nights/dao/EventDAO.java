package ch.jolly.nights.dao;

import ch.jolly.nights.bo.Status;
import ch.jolly.nights.entity.EventEntity;
import ch.jolly.nights.entity.ParticipantEntity;
import ch.jolly.nights.entity.StatusEntity;
import ch.jolly.nights.entity.UserEntity;
import ch.jolly.nights.util.JPAUtil;
import ch.jolly.nights.util.SessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EventDAO {

    public static void addEvent(EventEntity newEvent) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(newEvent);

        ParticipantEntity p = new ParticipantEntity(newEvent, entityManager.find(UserEntity.class, SessionUtil.getUser().getId()), entityManager.find(StatusEntity.class, 1));
        entityManager.persist(p);

        newEvent.getInvited().add(p);

        for (UserEntity userEntity : SessionUtil.getInvited()) {
            ParticipantEntity invited = new ParticipantEntity(newEvent, entityManager.find(UserEntity.class, userEntity.getId()), entityManager.find(StatusEntity.class, 2));
            entityManager.persist(invited);
            newEvent.getInvited().add(invited);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void deleteEvent(EventEntity deleteEvent) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(deleteEvent) ? deleteEvent : entityManager.merge(deleteEvent));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void editEventStatus(EventEntity editEvent, Status newStatus) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        EventEntity event = entityManager.find(EventEntity.class, editEvent.getId());

        for (ParticipantEntity part : event.getInvited()) {
            if (part.getUser().getId() == SessionUtil.getUser().getId()) {
                part.setStatus(entityManager.find(StatusEntity.class, newStatus.getKey()));
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void editEvent(EventEntity editEvent) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        for (UserEntity userEntity : SessionUtil.getInvited()) {
            ParticipantEntity invited = new ParticipantEntity(editEvent, entityManager.find(UserEntity.class, userEntity.getId()), entityManager.find(StatusEntity.class, 2));
            entityManager.persist(invited);
            editEvent.getInvited().add(invited);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static List<EventEntity> getEventByStatus(Status status) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery(
                "getEventsByStatus");
        query.setParameter("userId", SessionUtil.getUser().getId());
        query.setParameter("statusId", status.getKey());
        List<EventEntity> list = query.getResultList();
        entityManager.close();

        return list;

    }

    public static EventEntity getEvent(int Id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = entityManager.createNamedQuery(
                "getEvent");
        query.setParameter("eventId", Id);
        List<EventEntity> list = query.getResultList();
        entityManager.close();
        return list.get(0);

    }
}
