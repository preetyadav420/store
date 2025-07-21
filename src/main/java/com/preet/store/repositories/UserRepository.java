package com.preet.store.repositories;

import com.preet.store.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
        boolean existsUserByEmail(String email);

        Optional<User> findUserByEmail(String email);
}
