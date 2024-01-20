package bek.uz.securityjwt.service;

import bek.uz.securityjwt.entity.User;
import bek.uz.securityjwt.exceptions.AuthError;
import bek.uz.securityjwt.payload.JwtRequest;
import bek.uz.securityjwt.payload.JwtResponse;
import bek.uz.securityjwt.payload.RegisterDto;
import bek.uz.securityjwt.payload.UserDto;
import bek.uz.securityjwt.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AuthError(HttpStatus.UNAUTHORIZED.value(), "Login or password is wrong"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public ResponseEntity<?> register(RegisterDto registerDto){
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())){
            return new ResponseEntity<>(new AuthError(HttpStatus.BAD_REQUEST.value(), "Passwords are not confirm"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registerDto.getUsername()).isPresent()){
            return new ResponseEntity<>(new AuthError(HttpStatus.BAD_REQUEST.value(), "Username alrady exist"), HttpStatus.BAD_REQUEST);
        }
        User user = userService.createNewUser(registerDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getPhoneNumber()));
    }
}
