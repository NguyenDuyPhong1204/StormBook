package com.api.stormbook.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//id tu dong tang
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    private String avatar = "";

    private Boolean verified = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.ACTIVE;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

//    @OneToMany(mappedBy = "users")
//    private List<Report> reports;
//
//    @OneToMany(mappedBy = "users")
//    private List<Favorite> favorites;
//
//    @OneToMany(mappedBy = "users")
//    private List<Rating> ratings;
//
//    @OneToMany(mappedBy = "users")
//    private List<Notification> notifications;
//
//    @OneToMany(mappedBy = "users")
//    private List<Comment> comments;
//
//    @OneToMany(mappedBy = "users")
//    private List<ReadingHistory> histories;

    public enum Role{
        USER,STORY_USER, ADMIN
    }

    public enum Status{
        ACTIVE, BAN
    }

    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }


}
