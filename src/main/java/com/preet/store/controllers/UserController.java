package com.preet.store.controllers;


import com.preet.store.dto.*;
import com.preet.store.entities.Role;
import com.preet.store.entities.User;
import com.preet.store.services.ProductService;
import com.preet.store.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public Iterable<UserDto> getAllUsers()
    {   List<UserDto> users = new ArrayList<UserDto>();
        userService.getAllUsers().forEach(user -> {
            users.add(new UserDto(user.getId(),user.getName(),user.getEmail()));
        });
        return users::iterator;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id)
    {
        User user = userService.getUserById(id);
        if(user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new UserDto(user.getId(),user.getName(),user.getEmail()));
    }

    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody CreateUserDto data){

        if(userService.getUserByEmail(data.getEmail()))
                return ResponseEntity.badRequest().body(Map.of("error","User with same email is already there."));

        User user = new User();
        user.setName(data.getName());
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setEmail(data.getEmail());

        userService.save(user);

        return ResponseEntity.created(null).body(new UserDto(user.getId(),user.getName(),user.getEmail()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto data, @PathVariable Long id)
    {
        User user = userService.getUserById(id);
        if(user==null)
            return ResponseEntity.notFound().build();

        user.setName(data.getName());
        user.setEmail(data.getEmail());

        userService.save(user);

        return ResponseEntity.ok(new UserDto(user.getId(), user.getName(), user.getEmail()));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        User user = userService.getUserById(id);
        if(user==null)
            return ResponseEntity.notFound().build();
        
        userService.delete(user);
        
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangeUserPasswordDto data, @PathVariable Long id){
        User user = userService.getUserById(id);
        if(user==null)
            return ResponseEntity.notFound().build();

        else if(!user.getPassword().equals(data.getOldPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        user.setPassword(data.getNewPassword());

        userService.save(user);

        return ResponseEntity.noContent().build();
    }

}
