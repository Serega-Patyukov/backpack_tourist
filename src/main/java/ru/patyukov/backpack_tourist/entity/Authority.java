package ru.patyukov.backpack_tourist.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @JoinColumn(name = "user_login", nullable = false)
    @ManyToOne()
    private User user;
}
