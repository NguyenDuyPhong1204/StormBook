package com.api.stormbook.controller.FavoriteController;

import com.api.stormbook.dto.FavoriteDTO.FavoriteDTO;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.response.ApiNoDataResponse;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.service.FavoriteService.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<?> addToFavorite(
            @RequestBody FavoriteDTO favoriteDTO
    ){
        try{
            FavoriteDTO result = favoriteService.addStoryToFavorite(favoriteDTO);
            ApiResponse<FavoriteDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Thêm vào yêu thích thành công!", result);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw new ErrorException("Lỗi khi thêm vào yêu thích!" + e.getMessage());
        }
    }

    @GetMapping("/list-favorite/{userId}")
    public ResponseEntity<?> listFavorite(
            @PathVariable Long userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ){
        try {
            Page<Story> listFavorite = favoriteService.getListFavorite(userId, page, size);
            if(listFavorite.isEmpty()){
                throw new NotFoundException("Chưa thêm truyện nào vào yêu thích!");
            }
            ApiResponse<List<Story>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách truyện thành công!", listFavorite.getContent());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ErrorException("Lỗi lấy danh sách!" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable Long id){
        try {
            favoriteService.deleteStoryFavorite(id);
            ApiNoDataResponse apiNoDataResponse = new ApiNoDataResponse(HttpStatus.OK.value(), "Đã xóa truyện khỏi yêu thích");
            return ResponseEntity.ok(apiNoDataResponse);
        }catch (Exception e){
            throw new ErrorException("Lỗi xóa: "+ e.getMessage());
        }
    }
}
