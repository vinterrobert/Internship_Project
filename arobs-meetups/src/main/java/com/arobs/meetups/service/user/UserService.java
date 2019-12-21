package com.arobs.meetups.service.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto findById(int id) throws ClassNotFoundException;

    List<UserDto> getAllUsers();

    void createUser(UserDto userDTO);
    public void updateUser(int id, UserDto updatedUser);
    public UserDto findByEmail(String email);
    public void deleteUser(int id);
}
