package com.labs.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @JsonProperty
    private String id;

    @JsonProperty
    private String username;

    public static UserResponse toResponse(final String id, final String username) {
        return new UserResponse(id, username);
    }
}
