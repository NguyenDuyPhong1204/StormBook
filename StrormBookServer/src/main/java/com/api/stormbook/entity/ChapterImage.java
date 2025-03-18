package com.api.stormbook.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "chapter_images")
public class ChapterImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
