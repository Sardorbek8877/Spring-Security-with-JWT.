package bek.uz.securityjwt.controller;

import bek.uz.securityjwt.exceptions.AuthError;
import bek.uz.securityjwt.payload.JwtRequest;
import bek.uz.securityjwt.payload.JwtResponse;
import bek.uz.securityjwt.payload.RegisterDto;
import bek.uz.securityjwt.service.AuthService;
import bek.uz.securityjwt.service.UserService;
import bek.uz.securityjwt.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        return authService.register(registerDto);
    }
}
