package com.labs.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

	@JsonProperty
	private String userName;

	private String loginName;

	@JsonProperty
	private String password;

	@JsonProperty
	private String confirmPassword;


}
