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
    public List<UserDto> getAll() {return userObject.getAllUsers();}

    @Override
    @Transactional
    public void create(UserDto newUser) {
        userObject.create(newUser);
    }

    @Override
    @Transactional
    public void update(int idUser, UserDto updatedUser) {
        userObject.update(idUser, updatedUser);
    }

    @Override
    @Transactional
    public void delete(int idUser) {
        userObject.delete(idUser);
    }

}
