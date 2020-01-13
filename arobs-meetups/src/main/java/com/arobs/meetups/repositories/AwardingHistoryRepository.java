package com.arobs.meetups.repositories;

import com.arobs.meetups.entities.AwardingHistory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AwardingHistoryRepository {

    @Autowired
    SessionFactory sessionFactory;

    public AwardingHistory findById(int idAwardingHistory){
        Session session = sessionFactory.getCurrentSession();
        return session.get(AwardingHistory.class, idAwardingHistory);
    }

    public List<AwardingHistory> getAll(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM AwardingHistory").getResultList();
    }

    public List<AwardingHistory> getAllOfAnUser(int idUser){
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("SELECT * FROM awarding_history WHERE user_id =?")
                .setParameter(1, idUser)
                .addEntity(AwardingHistory.class)
                .list();
    }

    public List<Award> getAllOfTheYear(String year){
        Session session = sessionFactory.getCurrentSession();
        List<Award> awards = new ArrayList<>();
        List<Object[]> awardsObject = session.createNativeQuery("" +
                "SELECT users.first_name, users.last_name, SUM(number_of_points) as total_points\n" +
                "FROM awarding_history\n" +
                "INNER JOIN users ON users.user_id = awarding_history.user_id\n" +
                "WHERE YEAR(awarding_history.awarding_date) =?\n" +
                "GROUP BY(awarding_history.user_id)\n" +
                "ORDER BY(total_points) DESC;").setParameter(1, year).list();

        if(!awardsObject.isEmpty()){
            for(Object[] awardObject : awardsObject){
                awards.add(new Award((String)awardObject[0], (String)awardObject[1], ((BigDecimal)awardObject[2]).intValue()));
            }
        }
        return awards;
    }

    public void create(AwardingHistory newAwardingHistory){
        Session session = sessionFactory.getCurrentSession();
        session.save(newAwardingHistory);
    }

    public void update(AwardingHistory updatedAwardingHistory){
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedAwardingHistory);
    }

    public void delete(AwardingHistory awardingHistoryToDelete){
        Session session = sessionFactory.getCurrentSession();
        session.delete(awardingHistoryToDelete);
    }
}
