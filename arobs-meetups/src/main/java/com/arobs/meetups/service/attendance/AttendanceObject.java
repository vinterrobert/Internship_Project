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

    public AttendanceDto findById(int idAttendance) throws Exception{
        Attendance requestedAttendance = attendanceRepository.findById(idAttendance);
        if(requestedAttendance != null){
            return attendanceMapper.map(requestedAttendance, AttendanceDto.class);
        }else
        {
            throw new Exception("Attendance doesn't exist!");
        }
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

    public void create(int idUser, int idEvent) throws Exception{
        User requestedUser = userRepository.findById(idUser);
        Event requestedEvent = eventRepository.findById(idEvent);
        if(requestedEvent == null || requestedUser == null){
            throw new Exception ("User or Event doesn't exist!");
        }else {
            Attendance newAttendance = new Attendance(requestedUser, requestedEvent);
            if (!requestedEvent.isClosed()) {
                if (requestedEvent.getAttendees().size() < requestedEvent.getMaximumPeople()) {
                    attendanceRepository.create(newAttendance);
                }
            }
        }
    }

    public void update(int idAttendance, String comment, int note) throws Exception{
        Attendance requestedAttendance = attendanceRepository.findById(idAttendance);
        Event attendedEvent = requestedAttendance.getEvent();
        if(attendedEvent != null){
            if(attendedEvent.isClosed()) {
                giveAttendeePointsForFeedback(requestedAttendance, comment, note);
                attendanceRepository.update(requestedAttendance);
            }else{
                throw new Exception ("Event hasn't finished yet!");
            }
        }else{
            throw new Exception("Attendance doesn't exist!");
        }
    }

    public void giveAttendeePointsForFeedback(Attendance attendance, String comment, int note){
        User attendee = attendance.getUser();
        int attendeeCurrentPoints = attendee.getPoints();
        attendance.setComment(comment);
        attendance.setNote(note);
        attendee.setPoints(attendeeCurrentPoints + 2);
        userRepository.update(attendee);
    }

    public void delete(int idAttendance) throws Exception{
        Attendance attendanceToDelete = attendanceRepository.findById(idAttendance);
        if(attendanceToDelete != null){
            Event event = attendanceToDelete.getEvent();
            if(event.isClosed()){
                throw new Exception("Event has been presented. You can't delete the attendance");
            }else{
                attendanceRepository.delete(attendanceToDelete);
            }
        }else
        {
            throw new Exception("Attendance doesn't exist!");
        }
    }
}