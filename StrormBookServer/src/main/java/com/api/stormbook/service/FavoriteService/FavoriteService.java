package com.api.stormbook.service.FavoriteService;

import com.api.stormbook.dto.FavoriteDTO.FavoriteDTO;
import com.api.stormbook.entity.Favorite;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.repository.FavoriteRepository.FavoriteRepository;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private StoryRepository storyRepository;
    public FavoriteDTO addStoryToFavorite(FavoriteDTO favoriteDTO) {
        Favorite favorite = new Favorite();
        //lay thong tin user
        User user = authUserRepository.findById(favoriteDTO.getUserId()).orElseThrow(() -> new NotFoundException("Không tìm thấy người dùng với Id: "+ favoriteDTO.getUserId()));
        favorite.setUser(user);
        //lay thong tin story
        Story story = storyRepository.findStoryById(favoriteDTO.getStoryId());
        if(story == null) {
            throw new NotFoundException("Không tìm thấy truyện với ID: " + favoriteDTO.getStoryId());
        }
        favorite.setStory(story);

        if(favoriteRepository.existsByStoryIdAndUserId(favoriteDTO.getStoryId(), user.getId())) {
            throw new ErrorException("Truyện này đã được thêm vào yêu thích trước đó!");
        }

        favoriteRepository.save(favorite);
        return new FavoriteDTO(
          favorite.getId(),
          favorite.getUser().getId(),
          favorite.getStory().getId(),
          favorite.getCreatedAt()
        );
    }

    public Page<Story> getListFavorite(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return favoriteRepository.findFavoriteStoriesByUserId(userId, pageable);
    }

    public void deleteStoryFavorite(Long id){
        if(!favoriteRepository.existsById(id)){
            throw new NotFoundException("Không tìm thấy truyện với ID: " + id);
        }
        favoriteRepository.deleteById(id);
    }

}
