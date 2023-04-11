package com.souraj.demo3.service;

import com.souraj.demo3.RequestDto.UserRequestDto;
import com.souraj.demo3.model.User;

public interface UserService {

    public User save(UserRequestDto userRequestDto);

    public boolean isEmailExists(String email);

    public void deleteUser(String username);


}
