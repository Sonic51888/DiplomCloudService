package ru.netology.diplom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@Builder
//@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    @JsonProperty("auth-token")
    private String token;

//    public String getAuthToken() {
//        return  token;
//    }
}