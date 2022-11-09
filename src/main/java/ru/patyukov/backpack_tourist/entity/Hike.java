package ru.patyukov.backpack_tourist.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Hike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String dateStart;

    @Column(nullable = false)
    private String dateFinish;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "user_login", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "hike",
            cascade = CascadeType.ALL)
    private List<Equipment> equipmentList = new ArrayList<>();

    private String notes;
}
