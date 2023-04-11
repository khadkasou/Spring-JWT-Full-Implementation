package com.souraj.demo3.service;

import com.souraj.demo3.exceptions.GlobalExceptions;
import com.souraj.demo3.model.User;
import com.souraj.demo3.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =userRepo.findUserByUsername(username);

        if (user== null)new GlobalExceptions("User does not exists ");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
               mapRolesToAuthority(user) );

    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthority(User user){
        List<GrantedAuthority> authorities =user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return authorities;
    }
}
