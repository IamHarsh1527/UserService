package com.ecommerce.UserService.DTO;

import com.ecommerce.UserService.model.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String email;

    public static UserDto from(User saved) {
        UserDto userDto = new UserDto();
        userDto.setId(saved.getId());
        userDto.setEmail(saved.getEmail());
        return userDto;
    }
}
