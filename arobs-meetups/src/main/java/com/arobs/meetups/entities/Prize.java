package com.arobs.meetups.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "prize")
public class Prize {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "prize_id", nullable = false)
    private int id;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "value", nullable = false)
    @Min(value = 0, message = "Value must be positive")
    private int value;

    @OneToMany(
            mappedBy = "prize",
            cascade = CascadeType.ALL
    )
    Set<AwardingHistory> awards = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
