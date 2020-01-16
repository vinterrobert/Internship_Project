package com.arobs.meetups.controllers;

import com.arobs.meetups.service.user.UserDto;
import com.arobs.meetups.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/id{idUser}")
    public ResponseEntity<UserDto> findUserById(@PathVariable int idUser){
        return ResponseEntity.ok(userService.findById(idUser));
    }

    @GetMapping(path = "/email{email}")
    public ResponseEntity<UserDto> findUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @GetMapping (path = "/allUsers")
    public ResponseEntity<List<UserDto>> getAllUsers (){
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping (path = "/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserDto newUser) {
        userService.create(newUser);
        return ResponseEntity.ok("User created");
    }

    @PutMapping(path = "/updateUser{id}")
    public ResponseEntity<String> updateUser (@RequestBody UserDto updatedUser, @PathVariable int id){
        userService.update(id, updatedUser);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping(path = "/deleteUser{id}")
    public ResponseEntity<String> deleteUser (@PathVariable int id){
        userService.delete(id);
        return ResponseEntity.ok("User deleted");
    }

}
