package com.api.stormbook.dto.CommentDTO;

import com.api.stormbook.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private Long userId;

    private Long storyId;

    private Long chapterId;
    private Long parentId; //binh luan cha

    private List<CommentResponseDTO> replies;

    private String content;
    private String fullName;  // Thông tin user
    private String avatar;
    private Instant createdAt;

    public CommentResponseDTO(Long id, Long userId, Long storyId, Long chapterId, Long parentId, String content, String avatar, String fullName, Instant createdAt) {
        this.id = id;
        this.userId = userId;
        this.storyId = storyId;
        this.chapterId = chapterId;
        this.parentId = parentId;
        this.content = content;
        this.avatar = avatar;
        this.fullName = fullName;
        this.createdAt = createdAt;
    }
}
