package bek.uz.securityjwt.payload;

import lombok.Data;

@Data
public class JwtRequest {

    private String username;
    private String password;
}
