package com.api.stormbook.service.StoryService;

import com.api.stormbook.dto.StoryDTO.StoryDTO;
import com.api.stormbook.entity.Story;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoryUserService {
    @Autowired
    private StoryRepository storyRepository;

    //lay thong tin truyen theo id
    public StoryDTO getStoryById(Long storyId) {
        Optional<Story> story = storyRepository.findById(storyId);
        if(story.isEmpty()){
            throw new NotFoundException("Không tìm thấy truyện!");
        }
        Story storyInfo = story.get();
        return new StoryDTO(

        );

    }

}
