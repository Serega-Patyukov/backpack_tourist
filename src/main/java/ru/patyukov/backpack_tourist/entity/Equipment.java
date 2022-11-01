package ru.patyukov.backpack_tourist.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupEquipment grp;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "hike_id", nullable = false)
    private Hike hike;

    private double weight;
    private String notes;
}
