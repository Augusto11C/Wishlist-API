package com.labs.demo.model.dto;

import com.labs.demo.model.persistence.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {


    private String id;

    private String username;

    private String loginName;

    private String password;

    private String confirmPassword;


    public static UserDTO toDTO(final String id, final String username, final String loginName, final String password, final String confirmPassword) {
        return new UserDTO(id, username, loginName, password, confirmPassword);
    }

}
