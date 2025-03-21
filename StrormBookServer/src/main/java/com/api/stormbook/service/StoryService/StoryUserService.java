package com.api.stormbook.service.StoryService;

import com.api.stormbook.dto.StoryDTO.StoryDTO;
import com.api.stormbook.entity.Category;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoryUserService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private AuthUserRepository authUserRepository;

    //lay danh sach truyen da doc
    public List<StoryDTO> getReadStoryByUser(Long userId, int page, int size) {
        User user = authUserRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Người dùng không tồn tại"));
        //lay danh sach truyen ma user da doc
        Pageable pageable = PageRequest.of(page, size);
        Page<Story> storyList = storyRepository.findReadStoriesByUser(user.getId(), pageable);
        //chuyen danh sach thanh dto
        return storyList.stream()
                .map(story -> new StoryDTO(
                        story.getId(),
                        story.getTitle(),
                        story.getAuthor(),
                        story.getTransGroup(),
                        story.getCategories().stream().map(Category::getId).collect(Collectors.toList()),
                        story.getCover_image(),
                        story.getStatus(),
                        story.getView_count(),
                        story.getRating(),
                        story.getTotal_chapters(),
                        story.getDescription(),
                        story.getCreatedAt(),
                        story.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }


}
