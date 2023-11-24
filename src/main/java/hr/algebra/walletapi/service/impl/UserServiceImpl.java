package hr.algebra.walletapi.service.impl;


import hr.algebra.walletapi.model.Role;
import hr.algebra.walletapi.model.User;
import hr.algebra.walletapi.repository.UserRepository;
import hr.algebra.walletapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllPatient() {
        return userRepository.findAll().stream().filter((patinent) ->patinent.getRole() == Role.USER).collect(Collectors.toList());
    }
}
