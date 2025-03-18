package com.api.stormbook.controller.ChapterController;

import com.api.stormbook.dto.ChapterDTO.ChapterDTO;
import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.ChapterRepository.ChapterRepository;
import com.api.stormbook.service.ChapterService.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ChapterRepository chapterRepository;

    @GetMapping("/{chapterId}")
    public ResponseEntity<?> getChapter(@PathVariable("chapterId") Long chapterId){
        Chapter result = chapterRepository.findChapterById(chapterId);
        if(result == null){
            throw new NotFoundException("Không tìm thấy chương");
        }
        ChapterDTO chapterDTO = new ChapterDTO(result);
        ApiResponse<ChapterDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy thông tin chương thành công!", chapterDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list-chapter/{storyId}")
    public ResponseEntity<?> getChapterList(@PathVariable("storyId") Long storyId){
        List<ChapterDTO> chapterList = chapterService.getChapterByStoryId(storyId);
        if(chapterList.isEmpty()){
            throw new NotFoundException("Hiện truyện chưa có chương nào!");
        }
        ApiResponse<List<ChapterDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách chương thành công!", chapterList);
        return ResponseEntity.ok(response);
    }
}
