package com.arobs.meetups.service.attendance;

import com.arobs.meetups.entities.Attendance;
import com.arobs.meetups.entities.User;
import com.arobs.meetups.service.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface AttendanceService {
    AttendanceDto findById(int idAttendance) throws Exception;
    List<AttendanceDto> getAll();
    List<UserDto> getAllAttendeesForAnEvent(int idEvent);
    List<AttendanceDto> getAllAttendancesForAnEvent(int idEvent);
    double getAverageNoteForAnEvent (int idEvent);
    void create(int idUser, int idEvent) throws Exception;
    void update(int idAttendance, String comment, int note) throws Exception;
    void delete(int idAttendance) throws Exception;
}
