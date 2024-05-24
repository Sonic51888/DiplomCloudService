package ru.netology.diplom.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.netology.diplom.dto.User;
import ru.netology.diplom.repository.UserRepository;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@EnableJpaRepositories
public class UserService {

    @Autowired
    @Qualifier("userRepository")
    private final UserRepository userRepository;


    public UserDetails getUserByLogin(String login) {
        User user = userRepository.findByLogin(login);
        return user == null ? null : new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new ArrayList<>());
    }

    public void addTokenToUser(String login, String token) {
        userRepository.addTokenToUser(login, token);
    }

    public void logoutUser(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        userRepository.removeToken(token);
    }
}