package com.arobs.meetups.controllers;

import com.arobs.meetups.service.attendance.AttendanceDto;
import com.arobs.meetups.service.attendance.AttendanceService;
import com.arobs.meetups.service.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendances")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;

    @GetMapping(path = "/id{idAttendance}")
    public ResponseEntity findById(@PathVariable int idAttendance){
        try{
            return ResponseEntity.ok(attendanceService.findById(idAttendance));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Attendance doesn't exist!");
        }
    }

    @GetMapping (path = "/allAttendances")
    public ResponseEntity<List<AttendanceDto>> getAll (){
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
    public ResponseEntity create(@PathVariable int idUser, @PathVariable int idEvent) {
        try{
            attendanceService.create(idUser, idEvent);
            return ResponseEntity.status(HttpStatus.OK).body("Attendance created succesfully!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User or Event doesn't exist!");
        }
    }

    @PutMapping(path = "/updateAttendance{id}/comment{comment}/note{note}")
    public ResponseEntity<String> update(@PathVariable int id, @PathVariable String comment, @PathVariable int note){
        try {
            attendanceService.update(id, comment, note);
            return ResponseEntity.status(HttpStatus.OK).body("Attendance updated");
        }catch(Exception e){
            if(e.getMessage().equals("Event hasn't finished yet!")){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
    }

    @DeleteMapping(path = "/deleteAttendance{id}")
    public ResponseEntity<String> delete (@PathVariable int id){
        try{
            attendanceService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body("Attendance deleted");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Attendance doesn't exist!");
        }
    }
}
