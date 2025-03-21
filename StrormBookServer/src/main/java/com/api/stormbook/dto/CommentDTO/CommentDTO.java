package com.api.stormbook.dto.CommentDTO;

import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.Comment;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    private Long userId;

    private Long storyId;

    private Long chapterId;

    private Long parentId = null; //binh luan cha

    private List<Comment> replies;

    private String content;

    private Instant createdAt;

    private Instant updatedAt;

    public CommentDTO(Long id, Long userId, Long storyId, Long chapterId, List<Comment> replies, String content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.storyId = storyId;
        this.chapterId = chapterId;
        this.replies = replies;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CommentDTO(Long id, Long userId, Long storyId, Long chapterId, Long parentId, String content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.userId = userId;
        this.storyId = storyId;
        this.chapterId = chapterId;
        this.parentId = parentId;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
