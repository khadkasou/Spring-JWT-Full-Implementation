package com.souraj.demo3.service;

import com.souraj.demo3.RequestDto.UserRequestDto;
import com.souraj.demo3.exceptions.GlobalExceptions;
import com.souraj.demo3.model.Role;
import com.souraj.demo3.model.User;
import com.souraj.demo3.repository.RoleRepo;
import com.souraj.demo3.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final RoleRepo roleRepo;

    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public User save(UserRequestDto userRequestDto) {
        User us = new User();
        us.setFirstName(userRequestDto.getFirstName());
        us.setLastName(userRequestDto.getLastName());

        User userName= userRepo.findUserByUsername(userRequestDto.getUsername());
        if(!Objects.isNull(userName)){
            throw  new GlobalExceptions("User with username "+userName+" already exists");
        }
        us.setUsername(userRequestDto.getUsername());

        us.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        if (!userRequestDto.getConfirmPassword().equals(userRequestDto.getPassword())){
            throw new GlobalExceptions("Password you provide does not match with confirm password");
        }
        User userEmail = userRepo.findUserByEmail(userRequestDto.getEmail());
        if(!Objects.isNull(userEmail)){
            throw new GlobalExceptions("User with above email "+userEmail+" already exists");
        }
        us.setEmail(userRequestDto.getEmail());

        List<Role> roles= userRequestDto.getRoles();
        List<Role> roleList = new ArrayList<>();

        for (Role role: roles){
            if (roleRepo.findRoleByName(role.getName())!=null){
                    throw new GlobalExceptions("User with this role already exists");
            }
            roleList.add(roleRepo.findRoleByName(role.getName()));
        }
        us.setRoles(roles);


        return userRepo.save(us);
    }

    @Override
    public boolean isEmailExists(String email) {

        User user = userRepo.findUserByEmail(email);
        if(user == null) {
            return false;
        }else {
            return true;
        }
    }



    @Override
    public void deleteUser(String username) {
        User user = userRepo.findUserByUsername(username);
        if (user==null){
            throw new GlobalExceptions("User with username "+username+"not found");
        }
        userRepo.delete(user);
    }


}
