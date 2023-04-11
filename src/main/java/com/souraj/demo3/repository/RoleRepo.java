package com.souraj.demo3.repository;

import com.souraj.demo3.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role, String> {

    public Role findRoleByName(String name);

}
