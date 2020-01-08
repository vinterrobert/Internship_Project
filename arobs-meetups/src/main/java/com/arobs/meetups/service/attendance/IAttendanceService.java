package com.arobs.meetups.service.attendance;

import com.arobs.meetups.entities.Attendance;
import com.arobs.meetups.entities.User;
import com.arobs.meetups.service.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface IAttendanceService {
    AttendanceDto findById(int idAttendance);
    List<AttendanceDto> getAll();
    List<UserDto> getAllAttendeesForAnEvent(int idEvent);
    List<AttendanceDto> getAllAttendancesForAnEvent (int idEvent);
    double getAverageNoteForAnEvent (int idEvent);
    void createAttendance(int idUser, int idEvent);
    void updateAttendance(int idAttendance, String comment, int note);
    void deleteAttendance(int idAttendance);
}
