package com.labs.demo.controller;

import com.labs.demo.model.dto.WishlistDTO;
import com.labs.demo.model.request.ModifyWishlistRequest;
import com.labs.demo.model.request.WishlistResponse;
import com.labs.demo.service.WishlistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WishlistController.class);

    private WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }


    @PostMapping("/addToWishlist")
    public ResponseEntity<WishlistResponse> addToWishlist(@RequestBody @Validated ModifyWishlistRequest request) {
        final List<String> wishlistOptional = wishlistService.addToWishlist(WishlistDTO.toDTO(request.getLoginName(), request.getItemName()));

        return ResponseEntity.ok(WishlistResponse.toResponse(wishlistOptional));

//        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/removeFromWishlist")
    public ResponseEntity<WishlistResponse> removeFromWishlist(@RequestBody ModifyWishlistRequest request) {

        final List<String> wishlistOptional = wishlistService.removeFromWishlist(WishlistDTO.toDTO(request.getLoginName(), request.getItemName()));

        return ResponseEntity.ok(WishlistResponse.toResponse(wishlistOptional));

//        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/getWishlist/{loginName}")
    public ResponseEntity<WishlistResponse> getWishlist(@PathVariable String loginName) {

        final List<String> wishlistOptional = wishlistService.getWishlist(loginName);

        return ResponseEntity.ok(WishlistResponse.toResponse(wishlistOptional));

    }
}
