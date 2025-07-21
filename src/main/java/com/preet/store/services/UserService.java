package com.preet.store.services;


import com.preet.store.entities.Product;
import com.preet.store.entities.User;
import com.preet.store.repositories.ProductRepository;
import com.preet.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    ProductRepository productRepository;

    @Transactional
    public void addAllProductsToWishlist(){
        User user = userRepository.findById(2L).orElseThrow();

        Iterable<Product> products = productRepository.findAll();

        products.forEach(product->
        {
            System.out.println(product);
            user.getProducts().add(product);
        });

        userRepository.save(user);

    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
         userRepository.delete(user);
    }

    public boolean getUserByEmail( String email) {
        return userRepository.existsUserByEmail(email);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }
}
