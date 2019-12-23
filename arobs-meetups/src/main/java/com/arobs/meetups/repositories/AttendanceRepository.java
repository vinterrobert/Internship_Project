package com.arobs.meetups.repositories;

import com.arobs.meetups.entities.Attendance;;
import com.arobs.meetups.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.type.DoubleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttendanceRepository {

    @Autowired
    SessionFactory sessionFactory;

    public Attendance findById(int idAttendance){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Attendance.class, idAttendance);

    }

    public List<Attendance> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query hql = session.createQuery("from Attendance");
        return hql.getResultList();
    }

    public List<User> getAllAttendeesForAnEvent(int idEvent){
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("SELECT * FROM users INNER JOIN attendance ON attendance.user_id = users.user_id WHERE attendance.event_id =?")
                .addEntity(User.class)
                .setParameter(1, idEvent).list();
    }

    public List<Attendance> getAllAttendancesForAnEvent (int idEvent){
        Session session = sessionFactory.getCurrentSession();
        return session.createNativeQuery("Select * From attendance where event_id =?")
                .setParameter(1, idEvent).addEntity(Attendance.class).list();
    }

    public double getAverageNoteForAnEvent (int idEvent){
        Session session = sessionFactory.getCurrentSession();
        Number average = (Number)session.createNativeQuery("Select AVG(note) AS AverageNote FROM Attendance where event_id =?")
                .setParameter(1, idEvent).getSingleResult();
        return average.doubleValue();
    }

    public void createAttendance(Attendance newAttendance) {
        Session session = sessionFactory.getCurrentSession();
        session.save(newAttendance);
    }

    public void updateAttendance (Attendance updatedAttendance){
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedAttendance);
    }

    public void deleteAttendance(Attendance attendanceToDelete){
        Session session = sessionFactory.getCurrentSession();
        session.delete(attendanceToDelete);
    }

}
