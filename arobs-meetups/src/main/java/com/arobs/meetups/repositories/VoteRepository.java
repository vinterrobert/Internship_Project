package com.arobs.meetups.repositories;

import com.arobs.meetups.entities.Proposal;
import com.arobs.meetups.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class VoteRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProposalRepository proposalRepository;

    public Set<Proposal> getAllVotesOfAnUser(int idUser){
        Session session = sessionFactory.getCurrentSession();
        List<Proposal> votedProposals = session.createNativeQuery("SELECT proposal.* FROM proposal INNER JOIN vote ON vote.id_proposal = proposal.proposal_id WHERE vote.id_user =?")
                .setParameter(1, idUser).addEntity(Proposal.class).list();
        return votedProposals.stream().collect(Collectors.toSet());
    }

    public void createVote(int idUser, int idProposal){
        User user = userRepository.findById(idUser);
        Proposal proposal = proposalRepository.findById(idProposal);
        user.getVotedProposals().add(proposal);
        proposal.getUsers().add(user);
    }

    public Set<User> getAllVotesOfAProposal(int idProposal){
        Session session = sessionFactory.getCurrentSession();
        List<User> proposalVotes = session.createNativeQuery("SELECT users.* FROM users INNER JOIN vote ON vote.id_user = users.user_id WHERE vote.id_proposal =?")
                .setParameter(1, idProposal).addEntity(User.class).list();
        return proposalVotes.stream().collect(Collectors.toSet());
    }

    public void deleteVote(int idUser, int idProposal){
        Session session = sessionFactory.getCurrentSession();
        User voter = userRepository.findById(idUser);
        Proposal votedProposal = proposalRepository.findById(idProposal);
        voter.getVotedProposals().remove(votedProposal);
        votedProposal.getUsers().remove(voter);
    }

    public List<VotedProposal> getTopVotedProposals(){
        Session session = sessionFactory.getCurrentSession();
        List<Object[]> topVotedProposals = session.createNativeQuery("SELECT proposal.proposal_id, proposal.title, COUNT(vote.id_proposal) as votesNo FROM proposal INNER JOIN vote ON vote.id_proposal = proposal.proposal_id GROUP BY(vote.id_proposal) ORDER BY(votesNo) DESC;")
              .list();
        List<VotedProposal> votedProposals = new ArrayList<>();
        for(Object[] votedProposalObject : topVotedProposals){
            VotedProposal votedProposal = new VotedProposal(((Integer)votedProposalObject[0]).intValue(), (String)votedProposalObject[1], ((BigInteger)votedProposalObject[2]).intValue());
            votedProposals.add(votedProposal);
        }
        return votedProposals;
    }

}
