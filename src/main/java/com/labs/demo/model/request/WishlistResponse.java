package com.labs.demo.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class WishlistResponse {

    @JsonIgnore
    private Long id;

    @JsonProperty
    private List<String> items;


    public WishlistResponse(List<String> items) {
        this.items = items;
    }

    public static WishlistResponse toResponse(final List<String> items) {
        return new WishlistResponse(items);
    }
}
