package com.api.stormbook.service.RatingService;

import com.api.stormbook.dto.RatingDTO.RatingDTO;
import com.api.stormbook.entity.Rating;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.repository.RatingRepository.RatingRepository;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private StoryRepository storyRepository;

    public RatingDTO postRating(RatingDTO ratingDTO) {
        Rating rating = new Rating();
        //lay thong tin user
        User user = authUserRepository.findById(ratingDTO.getUserId()).orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng với ID: " + ratingDTO.getUserId()));
        //lay thong tin truyen
        Story story = storyRepository.findStoryById(ratingDTO.getStoryId());
        if(story == null) {
            throw new NotFoundException("Không tìm thấy truyện với ID: "+ ratingDTO.getStoryId());
        }
        rating.setUser(user);
        rating.setStory(story);
        rating.setReview(ratingDTO.getReview());
        rating.setRating(ratingDTO.getRating());
        ratingRepository.save(rating);

        //tinh rating trung binh cua truyen
        Double averageRating = ratingRepository.findAverageRatingByStoryId(story.getId());
        story.setRating(averageRating);
        storyRepository.save(story);

        return new RatingDTO(
                rating.getId(),
                rating.getUser().getId(),
                rating.getStory().getId(),
                rating.getRating(),
                rating.getReview(),
                rating.getCreatedAt()
        );

    }

}
