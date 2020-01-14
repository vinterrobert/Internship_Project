package com.arobs.meetups.service.user;

import com.arobs.meetups.entities.User;
import com.arobs.meetups.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserObject {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserDto findById(int idUser){
        User requestedUser = userRepository.findById(idUser);
        if(requestedUser != null)
            return userMapper.map(requestedUser, UserDto.class);
        return null;
    }

    public UserDto findByEmail (String email) {
        User requestedUser = userRepository.findByEmail(email);
        if (requestedUser != null) {
            return userMapper.map(requestedUser, UserDto.class);
        }
        return null;
    }

    public List<UserDto> getAllUsers(){
        List<User> requestedUsers = userRepository.getAll();
        List<UserDto> requestedDtoUsers = new ArrayList<>();
        if(!requestedUsers.isEmpty()){
            for(User user : requestedUsers){
                requestedDtoUsers.add(userMapper.map(user, UserDto.class));
            }
        }
        return requestedDtoUsers;
    }

    public void create(UserDto newUserDto){
        User newUser = userMapper.map(newUserDto, User.class);
        userRepository.create(newUser);
    }

    public void update(int id, UserDto updatedUserDto){
        User updatedUser = userMapper.map(updatedUserDto, User.class);
        User requestedUser = userRepository.findById(id);
        requestedUser.setFirstName(updatedUser.getFirstName());
        requestedUser.setLastName(updatedUser.getLastName());
        requestedUser.setEmail(updatedUser.getEmail());
        requestedUser.setPassword(updatedUser.getPassword());
        requestedUser.setPoints(updatedUser.getPoints());
        requestedUser.setRole(updatedUser.getRole());
        userRepository.update(requestedUser);
    }

    public void delete(int id){
        User userToDelete = userRepository.findById(id);
        userRepository.delete(userToDelete);

    }

}
