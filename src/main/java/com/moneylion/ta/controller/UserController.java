package com.moneylion.ta.controller;
// annotations
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
// model
import com.moneylion.ta.model.User;
// repository
import com.moneylion.ta.repository.UserRepository;


@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // endpoint to get all users
    @GetMapping("/users")
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }
    // endpoint to add new user
    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

}
