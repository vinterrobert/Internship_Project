package com.arobs.meetups.service.attendance;

import com.arobs.meetups.service.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AttendanceServiceImplementation implements IAttendanceService {

    @Autowired
    AttendanceObject attendanceObject;

    @Override
    @Transactional
    public AttendanceDto findById(int idAttendance) {
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
    public void createAttendance(AttendanceDto newAttendanceDto) {
        attendanceObject.createAttendance(newAttendanceDto);
    }

    @Override
    @Transactional
    public void updateAttendance(int idAttendance, AttendanceDto updatedAttendanceDto) {
        attendanceObject.updateAttendance(idAttendance, updatedAttendanceDto);
    }

    @Override
    @Transactional
    public void deleteAttendance(int idAttendance) {
        attendanceObject.deleteAttendnace(idAttendance);
    }
}
