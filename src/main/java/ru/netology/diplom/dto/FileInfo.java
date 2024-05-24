package ru.netology.diplom.dto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@ToString
@Table(name="files", schema = "diplom")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "size")
    private long size;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @Column(name = "content")
    private byte [] data;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
}