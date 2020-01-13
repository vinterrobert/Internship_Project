package com.arobs.meetups.controllers;

import com.arobs.meetups.service.attendance.AttendanceDto;
import com.arobs.meetups.service.attendance.AttendanceService;
import com.arobs.meetups.service.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping(path = "/id{idAttendance}")
    public ResponseEntity<AttendanceDto> findById(@PathVariable int idAttendance) throws ClassNotFoundException {
        return ResponseEntity.ok(attendanceService.findById(idAttendance));
    }


    @GetMapping (path = "/allAttendances")
    public ResponseEntity<List<AttendanceDto>> getAll () throws ClassNotFoundException{
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

    @PostMapping(path = "/createAttendance/user{idUser}/event{idEvent}")
    public ResponseEntity<String> create(@PathVariable int idUser, @PathVariable int idEvent) {
        attendanceService.create(idUser, idEvent);
        return ResponseEntity.ok("Attendance created");
    }

    @PutMapping(path = "/updateAttendance{id}/comment{comment}/note{note}")
    public ResponseEntity<String> update(@PathVariable int id, @PathVariable String comment, @PathVariable int note){
        attendanceService.update(id, comment, note);
        return ResponseEntity.ok("Attendance updated");
    }

    @DeleteMapping(path = "/deleteAttendance{id}")
    public ResponseEntity<String> delete (@PathVariable int id){
        attendanceService.delete(id);
        return ResponseEntity.ok("Attendance deleted");
    }

}
