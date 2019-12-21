package com.arobs.meetups.service.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto findById(int idUser) throws ClassNotFoundException;
    List<UserDto> getAllUsers();
    UserDto findByEmail(String email);
    void createUser(UserDto userDTO);
    void updateUser(int idUser, UserDto updatedUser);
    void deleteUser(int idUser);
}
