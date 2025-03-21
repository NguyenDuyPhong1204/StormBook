package com.api.stormbook.controller.ChapterController;

import com.api.stormbook.dto.ChapterDTO.ChapterDTO;
import com.api.stormbook.entity.response.ApiNoDataResponse;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.service.ChapterService.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/a/chapter")
public class ChapterAController {
    @Autowired
    private ChapterService chapterService;
    @PostMapping("/create")
    public ResponseEntity<?> createChapter(
            @RequestParam("chapterNumber") int chapterNumber,
            @RequestParam("title") String title,
            @RequestParam("storyId") Long storyId,
            @RequestParam("images")List<MultipartFile> imagesFile
            ){
        try{
            ChapterDTO chapterDTO = new ChapterDTO();
            chapterDTO.setChapterNumber(chapterNumber);
            chapterDTO.setTitle(title);
            chapterDTO.setStoryId(storyId);
            chapterDTO.setImageFiles(imagesFile);

            ChapterDTO result = chapterService.createChapter(chapterDTO);
            ApiResponse<ChapterDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Thêm mới chapter thành công!", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ErrorException("Lỗi tạo chapter: "+e.getMessage());
        }
    }

    @PutMapping("/{chapterId}")
    public ResponseEntity<?> updateChapter(
            @PathVariable Long chapterId,
            @RequestParam("chapterNumber") int chapterNumber,
            @RequestParam("title") String title,
            @RequestParam("storyId") Long storyId,
            @RequestParam("images")List<MultipartFile> imagesFile
    ){
        try{
            ChapterDTO chapterDTO = new ChapterDTO();
            chapterDTO.setChapterNumber(chapterNumber);
            chapterDTO.setTitle(title);
            chapterDTO.setStoryId(storyId);
            chapterDTO.setImageFiles(imagesFile);

            ChapterDTO result = chapterService.updateChapter(chapterId, chapterDTO);
            ApiResponse<ChapterDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Cập nhật chương thành công!", result);
            return ResponseEntity.ok(response);


        }catch (Exception e){
            throw new ErrorException("Lỗi cập nhật chapter: "+e.getMessage());
        }
    }

    @DeleteMapping("/{chapterId}")
    public ResponseEntity<?> deleteChapter(
            @PathVariable Long chapterId
    ){
        try{
            chapterService.deleteChapter(chapterId);
            ApiNoDataResponse response = new ApiNoDataResponse(HttpStatus.OK.value(), "Xóa chương thành công");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw new ErrorException("Lỗi xóa chương: "+ e.getMessage());
        }
    }
}
