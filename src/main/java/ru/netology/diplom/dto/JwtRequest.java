package ru.netology.diplom.dto;

import lombok.Data;

@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class JwtRequest {
    private String login;
    private String password;
}