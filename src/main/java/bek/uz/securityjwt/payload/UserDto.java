package bek.uz.securityjwt.payload;

import bek.uz.securityjwt.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String phoneNumber;
}
