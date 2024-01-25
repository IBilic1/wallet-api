package hr.algebra.walletapi.service.impl;


import hr.algebra.walletapi.model.User;
import hr.algebra.walletapi.repository.UserRepository;
import hr.algebra.walletapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getChildren(String email) {
        return userRepository.findByEmail(email).map(User::getChildren).get();
    }

    @Override
    public void addChildren(User child, String email) {
        child.setPassword(passwordEncoder.encode(child.getPassword()));
        userRepository.save(child);
        User user = userRepository.findByEmail(email).get();
        user.getChildren().add(child);
        userRepository.save(user);
    }
}
