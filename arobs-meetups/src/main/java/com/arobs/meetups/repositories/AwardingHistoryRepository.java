package com.arobs.meetups.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AwardingHistoryRepository {

    @Autowired
    SessionFactory sessionFactory;



}
