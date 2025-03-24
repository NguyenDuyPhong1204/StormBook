package com.api.stormbook.controller.RatingController;

import com.api.stormbook.dto.RatingDTO.RatingDTO;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.service.RatingService.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping("/post-rating")
    public ResponseEntity<?> postRating(@RequestBody RatingDTO ratingDTO){
        try {
            RatingDTO newRating = ratingService.postRating(ratingDTO);
            ApiResponse<RatingDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Đánh giá thành công", newRating);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ErrorException("Lỗi đánh giá!" + e.getMessage());
        }
    }
}
