package com.souraj.demo3.repository;

import com.souraj.demo3.RequestDto.UserRequestDto;
import com.souraj.demo3.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

    public User findUserByUsername(String username);
    public User findUserByEmail(String email);

    public User save (UserRequestDto userRequestDto);

}
