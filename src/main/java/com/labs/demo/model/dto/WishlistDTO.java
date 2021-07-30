package com.labs.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDTO {

    private String loginName;

    private String itemName;

    public static WishlistDTO toDTO(final String loginName, final String itemName) {
        return new WishlistDTO(loginName, itemName);
    }
}
