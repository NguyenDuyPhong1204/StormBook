package com.api.stormbook.controller.ReadingHistoryController;

import com.api.stormbook.dto.ReadingHistoryDTO.ReHistoryDTO;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.service.ReadingHistoryService.ReHiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/history")
public class ReHiController {
    @Autowired
    private ReHiService reHiService;
    @PostMapping("/read")
    public ResponseEntity<?> markAsRead(
            @RequestParam("userId") Long userId,
            @RequestParam("storyId") Long storyId,
            @RequestParam("chapterId") Long chapterId
    ) {
        ReHistoryDTO reHistoryDTO = reHiService.markChapterAsRead(userId, storyId, chapterId);
        ApiResponse<ReHistoryDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Đã lưu lịch sử đọc", reHistoryDTO);
        return ResponseEntity.ok(response);
    }
}
