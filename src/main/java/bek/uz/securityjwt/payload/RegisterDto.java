package bek.uz.securityjwt.payload;

import lombok.Data;

@Data
public class RegisterDto {

    private String username;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
}
