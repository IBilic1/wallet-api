package hr.algebra.walletapi.service;


import hr.algebra.walletapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUser(String email);

    List<User> getAllPatient();
}
