package ru.netology.diplom.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.netology.diplom.dto.AuthRequest;
import ru.netology.diplom.dto.AuthResponse;
import ru.netology.diplom.security.JwtTokenService;
import ru.netology.diplom.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    @Autowired
    private final JwtTokenService jwtTokenService;

    @Autowired
    private final UserService userService;


    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        final UserDetails userDetails = userService.getUserByLogin(authRequest.getLogin());
        if (userDetails != null) {
            var name = userDetails.getUsername();
            var pass = userDetails.getPassword();
            if (name.equals(authRequest.getLogin()) && pass.equals(authRequest.getPassword())) {
                final String token = jwtTokenService.generateToken(userDetails);
                userService.addTokenToUser(authRequest.getLogin(), token);
                return ResponseEntity.status(HttpStatus.OK).body(AuthResponse.builder().token(token).build());
            }
        }
        return ResponseEntity.status(400).body("Bad credentials");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String token) {
        userService.logoutUser(token);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}