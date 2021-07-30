package com.labs.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyWishlistRequest {
	
	@JsonProperty
	@NonNull
	private String loginName;
	
	@JsonProperty
	@NonNull
	private String itemName;

}
