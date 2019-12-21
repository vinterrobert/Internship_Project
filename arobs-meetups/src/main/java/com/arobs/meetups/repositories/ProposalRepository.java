package com.arobs.meetups.repositories;

import com.arobs.meetups.entities.Proposal;
import com.arobs.meetups.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ProposalRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Proposal findById(int proposalId) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Proposal.class, proposalId);
    }

    public User findByEmail(String email) {

        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from User u where u.email = :email")
                .setParameter("email", email);
        User foundUser = (User)hql.getSingleResult();
        return foundUser;
    }

    public List<Proposal> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Proposal");
        return hql.getResultList();
    }

    public List<Proposal> getAllWithSameDifficulty(String difficulty){

        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Proposal p where p.difficulty = :difficulty")
                .setParameter("difficulty", difficulty);
        return hql.getResultList();
    }

    public List<Proposal> getAllWithSameType(String type){

        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Proposal p where p.type = :type")
                .setParameter("type", type);
        return hql.getResultList();
    }

    public List<Proposal> getAllWithSameLanguage(String language){

        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Proposal p where p.language = :language")
                .setParameter("language", language);
        return hql.getResultList();
    }

    public List<Proposal> getAllOfAnUser (int idUser){
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("Select * From proposal where user_id=?")
                .addEntity(Proposal.class)
                .setParameter(1, idUser).list();
    }

    public void create(Proposal newProposal) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newProposal);
    }

    public void update(Proposal updatedProposal) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedProposal);
    }

    public void delete(Proposal proposalToDelete) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(proposalToDelete);
    }

}
