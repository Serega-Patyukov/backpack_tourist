package ru.patyukov.backpack_tourist.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    //todo следующее поле потом нужно будет удалить
    @Column(nullable = false)
    private String authority;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @Column(nullable = false)
    private List<Authority> authorities;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL)
    private List<Hike> hikeList = new ArrayList<>();

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    private String notes;
}
