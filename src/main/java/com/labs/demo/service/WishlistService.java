package com.labs.demo.service;

import com.labs.demo.model.dto.WishlistDTO;
import com.labs.demo.model.persistence.User;
import com.labs.demo.model.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WishlistService.class);

    private UserRepository userRepository;


    public WishlistService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getWishlist(final String loginName){
        User user = userRepository.findByLoginName(loginName);

        if (user == null) {
            LOGGER.warn("Event 'addToWishlist' error: user={} not found", loginName);
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        return user.getWishlist();

    }

    public List<String> addToWishlist(final WishlistDTO wishlistDTO) {
        LOGGER.info("Event 'addToWishlist', requested by userName={}", wishlistDTO.getLoginName());

        User user = userRepository.findByLoginName(wishlistDTO.getLoginName());

        if (user == null) {
            LOGGER.warn("Event 'addToWishlist' error: user={} not found", wishlistDTO.getLoginName());
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        List<String> wishlist = user.getWishlist();

        if (wishlist.size() < 20 && Objects.nonNull(wishlistDTO.getItemName())
                && !wishlistDTO.getItemName().equals("") && !wishlistDTO.getItemName().equals(" ")) {
            wishlist.add(wishlistDTO.getItemName().toUpperCase());
        }

        userRepository.save(user);

        LOGGER.info("Event 'addToWishlist' successfully finished: item added to wishlist for user={}", wishlistDTO.getLoginName());

        return wishlist;
    }

    public List<String> removeFromWishlist(final WishlistDTO wishlistDTO) {
        LOGGER.info("Event 'removeFromWishlist', requested by userName={}", wishlistDTO.getLoginName());

        final String itemToRemove = wishlistDTO.getItemName();

        User user = userRepository.findByLoginName(wishlistDTO.getLoginName());

        if (user == null) {
            LOGGER.warn("Event 'removeFromWishlist' error: user={} not found", wishlistDTO.getLoginName());
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        List<String> newWishList = user.getWishlist().stream()
                .filter(p -> !p.equalsIgnoreCase(itemToRemove))
                .collect(Collectors.toList());

        user.setWishlist(newWishList);

        userRepository.save(user);

        LOGGER.info("Event 'removeFromWishlist' successfully finished: item removed to cart for user={}", wishlistDTO.getLoginName());

        return newWishList;
    }
}
