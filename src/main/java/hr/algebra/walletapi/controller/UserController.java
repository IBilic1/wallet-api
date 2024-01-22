package hr.algebra.walletapi.controller;

import hr.algebra.walletapi.model.User;
import hr.algebra.walletapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> getUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.of(userService.getUser(principal.getUsername()));
    }

    @GetMapping("/children")
    public ResponseEntity<List<User>> getChildren() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userService.getChildren(principal.getUsername()));
    }

    @PostMapping("/children")
    public ResponseEntity<Void> addChild(@RequestBody User user) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userService.addChildren(user, principal.getUsername());
        return ResponseEntity.ok().build();
    }
}
