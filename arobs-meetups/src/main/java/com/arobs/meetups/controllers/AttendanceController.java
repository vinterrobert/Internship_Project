package com.arobs.meetups.controllers;

import com.arobs.meetups.service.attendance.AttendanceDto;
import com.arobs.meetups.service.attendance.IAttendanceService;
import com.arobs.meetups.service.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    IAttendanceService attendanceService;

    @GetMapping(path = "/id{idAttendance}")
    public ResponseEntity<AttendanceDto> findById(@PathVariable int idAttendance) throws ClassNotFoundException {
        return ResponseEntity.ok(attendanceService.findById(idAttendance));
    }


    @GetMapping (path = "/allAttendances")
    public ResponseEntity<List<AttendanceDto>> getAllAttendances () throws ClassNotFoundException{
        return ResponseEntity.ok(attendanceService.getAll());
    }

    @GetMapping (path = "/allAttendeesForEvent{idEvent}")
    public ResponseEntity<List<UserDto>> getAllAttendeesForAnEvent (@PathVariable int idEvent) throws ClassNotFoundException{
        return ResponseEntity.ok(attendanceService.getAllAttendeesForAnEvent(idEvent));
    }

    @GetMapping (path = "/allAttendancesForEvent{idEvent}")
    public ResponseEntity<List<AttendanceDto>> getAllAttendancesForAnEvent (@PathVariable int idEvent) throws ClassNotFoundException{
        return ResponseEntity.ok(attendanceService.getAllAttendancesForAnEvent(idEvent));
    }

    @GetMapping (path = "/averageNoteForEvent{idEvent}")
    public ResponseEntity<Double> getAverageNoteForAnEvent(@PathVariable int idEvent){
        return ResponseEntity.ok(attendanceService.getAverageNoteForAnEvent(idEvent));
    }

    @PostMapping(path = "/createAttendance")
    public ResponseEntity<String> createAttendance(@RequestBody AttendanceDto attendanceDto) {
        attendanceService.createAttendance(attendanceDto);
        return ResponseEntity.ok("Attendance created");
    }

    @PutMapping(path = "/updateAttendance{id}")
    public ResponseEntity<String> updateAttendance (@RequestBody AttendanceDto attendanceDto, @PathVariable int id){
        attendanceService.updateAttendance(id, attendanceDto);
        return ResponseEntity.ok("Attendance updated");
    }

    @DeleteMapping(path = "/deleteAttendance{id}")
    public ResponseEntity<String> deleteAttendance (@PathVariable int id){
        attendanceService.deleteAttendance(id);
        return ResponseEntity.ok("Attendance deleted");
    }

}
