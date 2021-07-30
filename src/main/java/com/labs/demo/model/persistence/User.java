package com.labs.demo.model.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
@Data
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String userName;

    private String loginName;

    private String password;

    private List<String> wishlist;

    public void addItem(String item) {
        if (wishlist == null) {
            wishlist = new ArrayList<>();
        }
        wishlist.add(item.toUpperCase());
    }

    public void removeItem(String item) {
        if (wishlist == null) {
            wishlist = new ArrayList<>();
        }
        wishlist.remove(item.toUpperCase());
    }

}
