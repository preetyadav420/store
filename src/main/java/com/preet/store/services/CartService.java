package com.preet.store.services;

import com.preet.store.entities.Cart;
import com.preet.store.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;

    public void save(Cart cart){
        cartRepository.save(cart);
    }

    public Cart findById(UUID cartId){
        return cartRepository.findById(cartId).orElse(null);
    }

    public Cart getCartById(UUID cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }
}
