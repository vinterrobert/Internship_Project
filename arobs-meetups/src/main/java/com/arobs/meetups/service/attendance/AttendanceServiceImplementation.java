package com.arobs.meetups.service.attendance;

import com.arobs.meetups.service.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AttendanceServiceImplementation implements AttendanceService {

    @Autowired
    AttendanceObject attendanceObject;

    @Override
    @Transactional
    public AttendanceDto findById(int idAttendance)  throws Exception{
        return attendanceObject.findById(idAttendance);
    }

    @Override
    @Transactional
    public List<AttendanceDto> getAll() {
        return attendanceObject.getAll();
    }

    @Override
    @Transactional
    public List<UserDto> getAllAttendeesForAnEvent(int idEvent) {
        return attendanceObject.getAllAttendeesForAnEvent(idEvent);
    }

    @Override
    @Transactional
    public List<AttendanceDto> getAllAttendancesForAnEvent(int idEvent) {
        return attendanceObject.getAllAttendancesForAnEvent(idEvent);
    }

    @Override
    @Transactional
    public double getAverageNoteForAnEvent(int idEvent) {
        return attendanceObject.getAverageNoteForAnEvent(idEvent);
    }

    @Override
    @Transactional
    public void create(int idUser, int idEvent) throws Exception{
        attendanceObject.create(idUser, idEvent);
    }

    @Override
    @Transactional
    public void update(int idAttendance, String comment, int note) throws Exception{
        attendanceObject.update(idAttendance, comment, note);
    }

    @Override
    @Transactional
    public void delete(int idAttendance) throws Exception{
        attendanceObject.delete(idAttendance);
    }
}
