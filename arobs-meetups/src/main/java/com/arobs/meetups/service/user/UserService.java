package com.arobs.meetups.service.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto findById(int idUser);
    List<UserDto> getAll();
    UserDto findByEmail(String email);
    void create(UserDto userDTO);
    void update(int idUser, UserDto updatedUser);
    void delete(int idUser);
}
