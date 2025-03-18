package com.api.stormbook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Story> stories;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
//    @ManyToMany(mappedBy = "categories")
//    private List<Story> stories;

}
