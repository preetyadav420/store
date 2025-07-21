package com.preet.store.controllers;

import com.preet.store.dto.*;
import com.preet.store.entities.Cart;
import com.preet.store.entities.CartItem;
import com.preet.store.entities.Product;
import com.preet.store.repositories.CartRepository;
import com.preet.store.services.CartService;
import com.preet.store.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("carts")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final CartRepository cartRepository;

    @PostMapping
    public ResponseEntity<CartDto> createDto() {
        Cart cart = new Cart();
        cartService.save(cart);

        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        return ResponseEntity.created(null).body(cartDto);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId,
            @RequestBody AddItemToCart data) {
        Cart cart = cartService.findById(cartId);
        if (cart == null)
            return ResponseEntity.notFound().build();

        Product product = productService.getProductById(data.getProductId());
        if (product == null)
            return ResponseEntity.badRequest().build();

        CartItem cartItem = null;

        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getId().equals(product.getId())) {
                cartItem = item;
                cartItem.setQuantity(item.getQuantity() + 1);
                break;
            }
        }

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }

        cartService.save(cart);
        CartItemDto cartItemDto = new CartItemDto(new CartProductDto(product.getId(), product.getName(), product.getPrice()), cartItem.getQuantity(), cartItem.getTotalPrice());

        return ResponseEntity.created(null).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID cartId) {
        Cart cart = cartService.getCartById(cartId);
        if (cart == null)
            return ResponseEntity.notFound().build();

        Set<CartItemDto> dtoItems = new HashSet<CartItemDto>();
        cart.getCartItems().forEach(cartItem -> {
            dtoItems.add(new CartItemDto(new CartProductDto(cartItem.getProduct().getId(), cartItem.getProduct().getName(), cartItem.getProduct().getPrice()), cartItem.getQuantity(), cartItem.getTotalPrice()));
        });
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setItems(dtoItems);
        cartDto.setTotalPrice(cart.getTotalPrice());

        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateCart(@PathVariable UUID cartId, @PathVariable Long productId, @Valid @RequestBody UpdateCartItemDto data) {

        Cart cart = cartService.getCartById(cartId);
        if(cart==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Cart not found."));

        CartItem cartItem = null;

        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getId().equals(productId)) {
                cartItem = item;
                break;
            }
        }
        if(cartItem == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Product not found in the cart"));

        cartItem.setQuantity(data.getQuantity());
        cartService.save(cart);

        CartItemDto cartItemDto = new CartItemDto(new CartProductDto(cartItem.getProduct().getId(), cartItem.getProduct().getName(), cartItem.getProduct().getPrice()), cartItem.getQuantity(), cartItem.getTotalPrice());
        return ResponseEntity.ok(cartItemDto);

    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> deleteIte(@PathVariable UUID cartId, @PathVariable Long productId){
        Cart cart = cartService.getCartById(cartId);
        if(cart==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Cart not found."));

        CartItem cartItem = null;

        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getId().equals(productId)) {
                cartItem = item;
                break;
            }
        }

        if(cartItem != null) {
            cart.getCartItems().remove(cartItem);
            cartItem.setCart(null);
        }

        cartService.save(cart);
         return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(@PathVariable UUID cartId)
    {
        Cart cart = cartService.getCartById(cartId);
        if(cart==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error","Cart not found."));

        cart.getCartItems().clear();
        cartService.save(cart);

        return ResponseEntity.noContent().build();
    }
}
