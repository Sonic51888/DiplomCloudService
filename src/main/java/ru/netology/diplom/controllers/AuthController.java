package ru.netology.diplom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.netology.diplom.dto.JwtRequest;
import ru.netology.diplom.dto.JwtResponse;
import ru.netology.diplom.exceptions.AppError;
import ru.netology.diplom.security.JwtTokenUtils;
import ru.netology.diplom.service.UserService;

@RestController
@RequiredArgsConstructor
//@AllArgsConstructor
@RequestMapping
@CrossOrigin(origins = "http://localhost:8080")
public class AuthController {

    @Autowired
    private final JwtTokenUtils jwtTokenUtils;

    @Autowired
    private final UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getLogin(),
                            authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(
                    new AppError(HttpStatus.UNAUTHORIZED.value(),
                            "Не правильный логин или пароль"),
                    HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getLogin());
        String token = jwtTokenUtils.generateToken(userDetails);
        userService.addTokenToUser(authRequest.getLogin(), token);
//        HttpHeaders headers = new HttpHeaders(); //Новое добавление
//        headers.add("Authorization", "Bearer " + token);
        return ResponseEntity.ok(/*new JwtResponse(token)*/)
                .header("Authorization", "Bearer " + token)
                .header("Access-Control-Allow-Origin","http://localhost:8080")
                .body(new JwtResponse(token));
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String token) {
        userService.logoutUser(token);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}