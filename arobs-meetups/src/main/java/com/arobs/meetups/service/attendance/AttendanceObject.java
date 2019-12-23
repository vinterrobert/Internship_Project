package com.arobs.meetups.service.attendance;

import com.arobs.meetups.entities.Attendance;
import com.arobs.meetups.entities.User;
import com.arobs.meetups.repositories.AttendanceRepository;
import com.arobs.meetups.service.user.UserDto;
import com.arobs.meetups.service.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttendanceObject {

    @Autowired
    AttendanceRepository attendanceRepository;

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

    public void createAttendance(AttendanceDto newAttendanceDto){
        Attendance newAttendance = attendanceMapper.map(newAttendanceDto, Attendance.class);
        attendanceRepository.createAttendance(newAttendance);
    }

    public void updateAttendance(int idAttendance, AttendanceDto updatedAttendanceDto){
        Attendance requestedAttendance = attendanceRepository.findById(idAttendance);
        Attendance updatedAttendance = attendanceMapper.map(updatedAttendanceDto, Attendance.class);
        requestedAttendance.setComment(updatedAttendance.getComment());
        requestedAttendance.setEvent(updatedAttendance.getEvent());
        requestedAttendance.setNote(updatedAttendance.getNote());
        requestedAttendance.setUser(updatedAttendance.getUser());
    }

    public void deleteAttendnace(int idAttendance){
        Attendance attendanceToDelete = attendanceRepository.findById(idAttendance);
        attendanceRepository.deleteAttendance(attendanceToDelete);
    }
}
