package com.api.stormbook.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String transGroup;

    @ManyToMany
    @JoinTable(
            name = "story_category",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @Column(nullable = false)
    private String cover_image;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private int view_count;

    @Column
    private int rating;

    @Column
    private int total_chapters;

    @Column
    private String description;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

//    @OneToMany(mappedBy = "stories")
//    private List<Chapter> chapters;
//
//    @OneToMany(mappedBy = "stories")
//    private List<Report> reports;
//
//    @ManyToMany(mappedBy = "stories")
//    private List<Favorite> favorites;
//
//    @OneToMany(mappedBy = "stories")
//    private List<Rating> ratings;
//
//    @OneToMany(mappedBy = "stories")
//    private List<Comment> comments;
//
//    @ManyToMany(mappedBy = "stories")
//    private List<ReadingHistory> histories;

    public enum Status{
        OnGoing, Completed, Dropped
    }
}
