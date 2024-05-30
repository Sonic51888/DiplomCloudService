package ru.netology.diplom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse {

    private String filename;
    private Long size;
//    @JsonProperty("Access-Control-Allow-Origin")
//    private String allowOrigins = "localhost:8080";

}