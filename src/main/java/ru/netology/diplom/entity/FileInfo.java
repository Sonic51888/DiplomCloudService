package ru.netology.diplom.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="files", schema = "netology")
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