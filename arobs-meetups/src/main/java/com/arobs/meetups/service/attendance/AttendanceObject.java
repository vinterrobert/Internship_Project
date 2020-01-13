package com.arobs.meetups.service.attendance;

import com.arobs.meetups.entities.Attendance;
import com.arobs.meetups.entities.Event;
import com.arobs.meetups.entities.User;
import com.arobs.meetups.repositories.AttendanceRepository;
import com.arobs.meetups.repositories.EventRepository;
import com.arobs.meetups.repositories.UserRepository;
import com.arobs.meetups.service.user.UserDto;
import com.arobs.meetups.service.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class AttendanceObject {

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    UserMapper userMapper;

    public AttendanceDto findById(int idAttendance){
        Attendance requestedAttendance = attendanceRepository.findById(idAttendance);
        if(requestedAttendance != null){
            return attendanceMapper.map(requestedAttendance, AttendanceDto.class);
        }
        return null;
    }

    public List<AttendanceDto> getAll(){
        List<Attendance> requestedAttendances = attendanceRepository.getAll();
        List<AttendanceDto> requestedAttendancesDto = new ArrayList<>();
        if(!requestedAttendances.isEmpty())
        {
            for(Attendance attendance : requestedAttendances){
                requestedAttendancesDto.add(attendanceMapper.map(attendance, AttendanceDto.class));
            }
        }
        return requestedAttendancesDto;
    }

    public List<UserDto> getAllAttendeesForAnEvent(int idEvent){
        List<User> requestedUsers = attendanceRepository.getAllAttendeesForAnEvent(idEvent);
        List<UserDto> requestedUsersDto = new ArrayList<>();
        if(!requestedUsers.isEmpty()){
            for(User user : requestedUsers){
                requestedUsersDto.add(userMapper.map(user, UserDto.class));
            }
        }
        return requestedUsersDto;

    }

    public List<AttendanceDto> getAllAttendancesForAnEvent (int idEvent){
        List<Attendance> requestedAttendances = attendanceRepository.getAllAttendancesForAnEvent(idEvent);
        List<AttendanceDto> requestedAttendancesDto = new ArrayList<>();
        if(!requestedAttendances.isEmpty()){
            for(Attendance attendance : requestedAttendances){
                requestedAttendancesDto.add(attendanceMapper.map(attendance, AttendanceDto.class));
            }
        }
        return requestedAttendancesDto;
    }

    public double getAverageNoteForAnEvent (int idEvent){
        return attendanceRepository.getAverageNoteForAnEvent(idEvent);
    }

    public void create(int idUser, int idEvent){
        User requestedUser = userRepository.findById(idUser);
        Event requestedEvent = eventRepository.findById(idEvent);
        Attendance newAttendance = new Attendance(requestedUser, requestedEvent);
        //Testing if the limit is reached
        if(requestedEvent.getAttendees().size() < requestedEvent.getMaximumPeople()) {
            attendanceRepository.create(newAttendance);
            //Updating attendee's points
            int attendeeUsersPoints = requestedUser.getPoints();
            requestedUser.setPoints(attendeeUsersPoints + 5);
            userRepository.update(requestedUser);
        }

    }

    public void update(int idAttendance, String comment, int note){
        Attendance requestedAttendance = attendanceRepository.findById(idAttendance);
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        Event attendedEvent = requestedAttendance.getEvent();
        User attendee = requestedAttendance.getUser();
        int attendeeCurrentPoints = attendee.getPoints();
        //Testing if the event took place
        if(currentDate.after(attendedEvent.getDate())) {
            requestedAttendance.setComment(comment);
            requestedAttendance.setNote(note);
            attendanceRepository.update(requestedAttendance);
            attendee.setPoints(attendeeCurrentPoints + 2);
            userRepository.update(attendee);
        }
    }

    public void delete(int idAttendance){
        Attendance attendanceToDelete = attendanceRepository.findById(idAttendance);
        //Updating attendee's points
        User attendee = attendanceToDelete.getUser();
        int currentAttendeePoints = attendee.getPoints();
        attendee.setPoints(currentAttendeePoints - 5);
        userRepository.update(attendee);
        //Delete attendance
        attendanceRepository.delete(attendanceToDelete);

    }
}
