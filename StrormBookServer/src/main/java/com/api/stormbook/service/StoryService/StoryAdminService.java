package com.api.stormbook.service.StoryService;

import com.api.stormbook.dto.StoryDTO.StoryDTO;
import com.api.stormbook.entity.Category;
import com.api.stormbook.entity.Story;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.CategoryRepository.CategoryRepository;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import com.api.stormbook.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StoryAdminService {

    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private final String UPLOAD_DIR = "uploads/";
    private ImageUtils imageUtils = new ImageUtils();

    public StoryDTO createStory(StoryDTO storyDTO) throws IOException {
        Story story = new Story();
        story.setTitle(storyDTO.getTitle());
        story.setAuthor(storyDTO.getAuthor());
        story.setTransGroup(storyDTO.getTransGroup());
        story.setStatus(storyDTO.getStatus());
        story.setView_count(0);
        story.setRating(0);
        story.setTotal_chapters(0);
        story.setDescription(storyDTO.getDescription());

        // Lấy danh sách Category từ ID
        List<Category> categories = categoryRepository.findAllById(storyDTO.getCategoryId());
        story.setCategories(categories);

        //xu ly anh
        if(storyDTO.getImageFile() != null && !storyDTO.getImageFile().isEmpty()) {
            String imageUrl = imageUtils.saveImage(storyDTO.getImageFile());
            story.setCover_image(imageUrl);
        }
        //lua vao database
        storyRepository.save(story);
        return new StoryDTO(
                story.getId(),
                story.getTitle(),
                story.getAuthor(),
                story.getTransGroup(),
                story.getCategories().stream().map(Category::getId).collect(Collectors.toList()), // Trả về danh sách ID
                story.getCover_image(),
                story.getStatus(),
                story.getView_count(),
                story.getLike_count(),
                story.getRating(),
                story.getTotal_chapters(),
                story.getDescription(),
                story.getCreatedAt(),
                story.getUpdatedAt(),
                null
        );
    }

    //cap nhat thong tin truyen
    public StoryDTO updateStory(Long storyId,StoryDTO storyDTO) throws IOException {
        //kiem tra xem truyen co ton tai hay khong
        Story storyInfo = storyRepository.findStoryById(storyId);
        if(storyInfo == null) {
            throw new NotFoundException("Không tìm thấy truyện với ID: "+ storyInfo);
        }

        storyInfo.setTitle(storyDTO.getTitle());
        storyInfo.setAuthor(storyDTO.getAuthor());
        storyInfo.setTransGroup(storyDTO.getTransGroup());
        storyInfo.setStatus(storyDTO.getStatus());
        storyInfo.setDescription(storyDTO.getDescription());
        storyInfo.setUpdatedAt(storyDTO.getUpdatedAt());

        //cap nhat the loai
        List<Category> categories = categoryRepository.findAllById(storyDTO.getCategoryId());
        storyInfo.setCategories(categories);

        //cap nhat anh bia neu co anh bia moi
        if(storyDTO.getImageFile() != null && !storyDTO.getImageFile().isEmpty()) {
            String imageUrl = imageUtils.saveImage(storyDTO.getImageFile());
            storyInfo.setCover_image(imageUrl);
        }

        storyRepository.save(storyInfo);
    return new StoryDTO(
            storyInfo.getId(),
            storyInfo.getTitle(),
            storyInfo.getAuthor(),
            storyInfo.getTransGroup(),
            storyInfo.getCategories().stream().map(Category::getId).collect(Collectors.toList()),
            storyInfo.getCover_image(),
            storyInfo.getStatus(),
            storyInfo.getView_count(),
            storyInfo.getLike_count(),
            storyInfo.getRating(),
            storyInfo.getTotal_chapters(),
            storyInfo.getDescription(),
            storyInfo.getCreatedAt(),
            storyInfo.getUpdatedAt(),
            null
    );
    }

    //xoa truyen
    public void deleteStory(Long storyId){
        if(!storyRepository.existsById(storyId)) {
            throw new NotFoundException("Không tìm thấy truyện với ID: "+ storyId);
        }
        storyRepository.deleteById(storyId);
    }


}
