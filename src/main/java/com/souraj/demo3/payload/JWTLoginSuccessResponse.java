package com.souraj.demo3.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JWTLoginSuccessResponse {
    private boolean success;
    private String token;

}
