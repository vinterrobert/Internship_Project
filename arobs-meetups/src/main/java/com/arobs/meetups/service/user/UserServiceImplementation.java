package com.arobs.meetups.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserServiceImplementation implements UserService {

    @Autowired
    UserObject userObject;

    @Override
    @Transactional
    public UserDto findById(int idUser) {
        return userObject.findById(idUser);
    }

    @Override
    @Transactional
    public UserDto findByEmail(String email) {return userObject.findByEmail(email);}

    @Override
    @Transactional
    public List<UserDto> getAllUsers() {return userObject.getAllUsers();}

    @Override
    @Transactional
    public void createUser(UserDto newUser) {
        userObject.createUser(newUser);
    }

    @Override
    @Transactional
    public void updateUser(int idUser, UserDto updatedUser) {
        userObject.updateUser(idUser, updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(int idUser) {
        userObject.deleteUser(idUser);
    }

}
