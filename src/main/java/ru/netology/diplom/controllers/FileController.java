package ru.netology.diplom.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom.entity.FileInfo;
import ru.netology.diplom.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
//@AllArgsConstructor
@RequestMapping
@CrossOrigin(origins = "http://localhost:8080")
public class FileController {

    @Autowired
    private final FileService fileService;

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/file")
    public ResponseEntity<?> saveFile(@RequestParam("filename") String fileName, @RequestBody MultipartFile file) throws IOException {
        fileService.upload(fileName, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/file")
    public ResponseEntity<?> deleteFile(@RequestParam("filename") String fileName)  {
        fileService.delete(fileName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/file")
    public ResponseEntity<?> getFile(@RequestParam("filename") String fileName) throws IOException {
        FileInfo file = fileService.getFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(file.getData());
    }

    @PutMapping(value = "/file")
    public ResponseEntity<?> updateFileName (@RequestParam("filename") String fileName,
                                             @RequestBody String newFileName) throws IOException {
        return new ResponseEntity<>(fileService.updateFileName(fileName, newFileName), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(value = "/list")
    public ResponseEntity<?> getListFile(@RequestParam("limit") int limit, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        return new ResponseEntity<>(fileService.show(limit), HttpStatus.OK);
    }

}