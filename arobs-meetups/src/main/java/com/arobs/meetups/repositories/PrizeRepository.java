package com.arobs.meetups.repositories;

import com.arobs.meetups.entities.Prize;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class PrizeRepository {

    @Autowired
    SessionFactory sessionFactory;

    public List<Prize> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Prize");
        return hql.getResultList();
    }

    public Prize findById(int idPrize){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Prize.class, idPrize);
    }

    public void create(Prize newPrize){
        Session session = sessionFactory.getCurrentSession();
        session.save(newPrize);
    }

    public void update(Prize updatedPrize){
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedPrize);
    }

    public void delete(Prize prizeToDelete){
        Session session = sessionFactory.getCurrentSession();
        session.delete(prizeToDelete);
    }

}
