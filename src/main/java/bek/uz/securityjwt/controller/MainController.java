package bek.uz.securityjwt.controller;

import bek.uz.securityjwt.entity.Role;
import bek.uz.securityjwt.entity.User;
import bek.uz.securityjwt.payload.RoleDto;
import bek.uz.securityjwt.payload.UserDto;
import bek.uz.securityjwt.repositories.RoleRepository;
import bek.uz.securityjwt.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @GetMapping("/unsecured")
    public String unsecuredData(){
        return "Unsecured Data";
    }

    @GetMapping("/secured")
    public String securedData(){
        return "Secured Data";
    }

    @GetMapping("/admin")
    public String adminData(){
        return "Admin Data";
    }

    @GetMapping("/info")
    public String userData(Principal principal){
        return principal.getName();
    }

    @PostMapping("/addRole")
    public String addRole(@RequestBody RoleDto roleDto){
        Role role1 = new Role();
        role1.setName(roleDto.getName());
        roleRepository.save(role1);
        return "Role added";
    }

//    @PostMapping("/addUser")
//    public String addUser(@RequestBody UserDto userDto){
//        User user1 = new User();
//        user1.setUsername(userDto.getUsername());
//        user1.setPassword(passwordEncoder.encode(userDto.ge()));
//        user1.setPhoneNumber(userDto.getPhoneNumber());
//        userRepository.save(user1);
//        return "User added";
//    }

}
