package com.arobs.meetups.repositories;

import com.arobs.meetups.entities.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class EventRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Event findById(int eventId) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Event.class, eventId);
    }

    public List<Event> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Event");
        return hql.getResultList();
    }

    public List<Event> getAllWithSameDifficulty(String difficulty){

        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Event e where e.difficulty = :difficulty")
                .setParameter("difficulty", difficulty);
        return hql.getResultList();
    }

    public List<Event> getAllWithSameLanguage(String language){

        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Event e where e.language = :language")
                .setParameter("language", language);
        return hql.getResultList();
    }

    public List<Event> getAllOfAnUser (int idUser){
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("Select * From event where user_id=?")
                .addEntity(Event.class)
                .setParameter(1, idUser).list();
    }

    public List<Event> getAllWithSameRoom (String room){
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("Select * From event where room=?")
                .addEntity(Event.class)
                .setParameter(1, room).list();
    }

    public List<Event> getAllFromAPeriod(Timestamp startDate, Timestamp endDate){

        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("Select * From event where event_date >=? and event_date <=? order by event_date")
                .addEntity(Event.class)
                .setParameter(1, startDate)
                .setParameter(2, endDate).list();

    }

    public void create(Event newEvent) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newEvent);
    }

    public void update(Event updatedEvent) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedEvent);
    }

    public void delete(Event eventToDelete) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(eventToDelete);
    }


}
