package com.api.stormbook.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "chapters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    @Column
    private int chapterNumber;

    @Column
    private String title;


    @Column
    private int view_count;

    @Column
    private Boolean is_read;

    @ElementCollection
    @CollectionTable(name = "chapter_images", joinColumns = @JoinColumn(name = "chapter_id"))
    @Column(name = "image_url")
    private List<String> image_url;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

//    @OneToMany(mappedBy = "chapters")
//    private List<Report> reports;
//
//    @OneToMany(mappedBy = "chapters")
//    private List<Comment> comments;
//
//    @ManyToMany(mappedBy = "chapters")
//    private List<ReadingHistory> histories;

}
