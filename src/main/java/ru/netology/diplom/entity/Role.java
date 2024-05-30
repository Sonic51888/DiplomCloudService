package ru.netology.diplom.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roles" , schema = "netology")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
}
