package com.souraj.demo3.controller;

import com.souraj.demo3.RequestDto.UserRequestDto;
import com.souraj.demo3.model.User;
import com.souraj.demo3.payload.JWTLoginSuccessResponse;
import com.souraj.demo3.payload.LoginRequest;
import com.souraj.demo3.service.UserService;
import com.souraj.demo3.utils.JwtRequestFilter;
import com.souraj.demo3.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final AuthenticationManager manager;

    @Autowired
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequestDto userRequestDto){

        User newUser = userService.save(userRequestDto);
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTLoginSuccessResponse> login(@RequestBody LoginRequest loginRequest) {
               Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String jwt = jwtUtils.generateToken(userDetails);
//
        return ResponseEntity.ok(new JWTLoginSuccessResponse(true,jwt));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser(){
        return new ResponseEntity<>("all user", HttpStatus.OK);
    }
}
