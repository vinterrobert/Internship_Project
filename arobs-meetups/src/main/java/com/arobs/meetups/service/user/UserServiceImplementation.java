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
    public UserDto findById(int id) {
        return userObject.findById(id);
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
    public void updateUser(int id, UserDto updatedUser) {
        userObject.updateUser(id, updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userObject.deleteUser(id);
    }

}
