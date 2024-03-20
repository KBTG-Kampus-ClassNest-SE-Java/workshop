package com.kampus.kbazaar.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private User[] users =
            new User[] {
                new User(1, "John Doe", "john@mailsy.com"),
                new User(2, "Jane Doe", "jane@mailsy.com"),
            };

    @GetMapping("/users")
    public User[] getAllUsers() {
        return users;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        for (User user : users) {
            if (user.getId() == Integer.parseInt(id)) {
                return ResponseEntity.ok(user);
            }
        }
        // TODO: return {"message": "User not found"}
        return ResponseEntity.notFound().build();
    }
}
