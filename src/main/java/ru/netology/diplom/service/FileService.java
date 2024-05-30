package ru.netology.diplom.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.netology.diplom.entity.FileInfo;
import ru.netology.diplom.dto.FileResponse;
import ru.netology.diplom.entity.User;
import ru.netology.diplom.repository.FileRepository;
import ru.netology.diplom.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
@EnableJpaRepositories
@Log4j2
public class FileService {

    @Autowired
    @Qualifier("fileRepository")
    private final FileRepository fileRepository;

    @Autowired
    private final UserRepository userRepository;


    public void upload(String fileName, MultipartFile files) throws IOException {
        var login = SecurityContextHolder.getContext().getAuthentication().getName();
        var file = FileInfo.builder()
                .name(fileName)
                .data(files.getBytes())
                .size(files.getSize())
               .user(userRepository.findByLogin(login))
                .uploadDate(LocalDate.now())
                .build();
        fileRepository.save(file);
        log.info("сохранен файл " + fileName);
    }

    public void delete(String fileName) throws IOException {
        fileRepository.removeFileByName(fileName);
        log.info("удален файл " + fileName);
    }

    public FileInfo getFile(String fileName) throws IOException {
        Optional<FileInfo> optionalFile = fileRepository.findByName(fileName);
        if (optionalFile.isEmpty()) {
            log.info("Файла с именем  " + fileName + "  не существует ");
            throw new IOException("Файла с именем  " + fileName + "  не существует ");
        }
        log.info("скачали файл " + optionalFile.get().getName());
        return optionalFile.get();
    }

    public Object show(int limit) {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userRepository.findByLogin(login);
        User user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        var fileList = fileRepository.findAllFilesByUser(user);
        List<FileResponse> list = new ArrayList<>();

        for (int i = 0; i < limit && i < fileList.size(); i++) {
            var file = fileList.get(i);
            var fileResponse = new FileResponse(file.getName(), file.getSize());
            list.add(fileResponse);
        }
        log.info("отобразили файлы " + list.toString());
        return list;
    }

    public Object updateFileName(String fileName, String newName) throws IOException {
        Optional<FileInfo> fileInfoOptional = fileRepository.findByName(fileName);
        if (fileInfoOptional.isEmpty()) {
            throw new IOException("Файла с именем  " + fileName + "  не существует ");
        }
        FileInfo file = fileInfoOptional.get();
        String newFileName = newName.substring((newName.indexOf(":\"") + 2), newName.lastIndexOf("\""));
        file.setName(newFileName);
        log.info("файл переименован в " + newFileName);
        fileRepository.save(file);
        return file;
    }
}