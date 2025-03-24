package com.api.stormbook.controller.CommentController;

import com.api.stormbook.dto.CommentDTO.CommentDTO;
import com.api.stormbook.dto.CommentDTO.CommentResponseDTO;
import com.api.stormbook.dto.FavoriteDTO.FavoriteDTO;
import com.api.stormbook.entity.Comment;
import com.api.stormbook.entity.response.ApiNoDataResponse;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.service.CommentService.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post-comment")
    public ResponseEntity<?> postComment(@RequestBody CommentDTO comment){
        try{
            CommentDTO commentDTO = commentService.createComment(comment);
            ApiResponse<CommentDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Bình luận thành công!", commentDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ErrorException("Lỗi comment!" + e.getMessage());
        }
    }

    @PostMapping("/reply-comment/{commentId}")
    public ResponseEntity<?> replyComment(@PathVariable Long commentId,@RequestBody CommentDTO commentDTO){
        try{
            CommentDTO comment = commentService.replyComment(commentId,commentDTO);
            ApiResponse<CommentDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Trả lời bình luận thành công!", comment);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw new ErrorException("Lỗi comment!" + e.getMessage());
        }
    }

    @GetMapping("/list-comment-chap/{chapterId}")
    public ResponseEntity<?> listCommentChap(@PathVariable Long chapterId){
        try{
            List<CommentResponseDTO> commentList = commentService.getCommentChapter(chapterId);
            ApiResponse<List<CommentResponseDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách comment thành công!", commentList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ErrorException("Lỗi lấy danh sách comment!" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id){
        try{
            commentService.deleteComment(id);
            ApiNoDataResponse noDataResponse = new ApiNoDataResponse(HttpStatus.OK.value(), "Xóa comment thành công!");
            return ResponseEntity.ok(noDataResponse);
        }catch (Exception e){
            throw new ErrorException("Lỗi xóa comment!" + e.getMessage());
        }
    }

    @DeleteMapping("/delete-all/{chapterId}")
    public ResponseEntity<?> deleteAllComment(@PathVariable Long chapterId){
        try {
            commentService.deleteAllComment(chapterId);
            ApiNoDataResponse response = new ApiNoDataResponse(HttpStatus.OK.value(), "Xóa comment thành công");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw new ErrorException("Lỗi xóa comment!" + e.getMessage());
        }
    }

    //chinh sua comment
    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Long id,@RequestBody CommentDTO commentDTO){
        try {
            CommentDTO comment = commentService.updateComment(id, commentDTO);
            ApiResponse<CommentDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Chỉnh sửa comment thành công!", comment);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ErrorException("Lỗi sửa comment!" + e.getMessage());
        }
    }

}
