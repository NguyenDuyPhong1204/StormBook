package com.api.stormbook.service.ChapterService;

import com.api.stormbook.dto.ChapterDTO.ChapterDTO;
import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.Story;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.ChapterRepository.ChapterRepository;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import com.api.stormbook.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private StoryRepository storyRepository;

    private ImageUtils imageUtils = new ImageUtils();
    public ChapterDTO createChapter(ChapterDTO chapterDTO) throws IOException {

        Optional<Story> storyInfo = storyRepository.findById(chapterDTO.getStoryId());

        if(!storyInfo.isPresent()){
            throw new NotFoundException("Không tìm thấy truyện nào!");
        }

        Story story = storyInfo.get();

        Chapter chapter = new Chapter();
        chapter.setTitle(chapterDTO.getTitle());
        chapter.setStory(story);
        chapter.setChapterNumber(chapterDTO.getChapterNumber());
        chapter.setView_count(chapterDTO.getView_count());
        chapter.setIs_read(chapterDTO.getIs_read());

        //xu ly anh
        if(chapterDTO.getImageFiles() != null && !chapterDTO.getImageFiles().isEmpty()){
            List<String> imagesPath = imageUtils.saveImages(chapterDTO.getImageFiles());
            chapter.setImage_url(imagesPath);
        }

        chapterRepository.save(chapter);
        return new ChapterDTO(
                chapter.getId(),
                chapter.getChapterNumber(),
                chapter.getTitle(),
                chapter.getStory().getId(),
                chapter.getView_count(),
                chapter.getIs_read(),
                chapter.getImage_url(),
                chapter.getCreatedAt(),
                chapter.getUpdatedAt(),
                null
        );
    }

    //cap nhat chapter
    public ChapterDTO updateChapter(Long chapterId, ChapterDTO chapterDTO) throws IOException {
       Chapter chapterInfo = chapterRepository.findChapterById(chapterId);

       if(chapterInfo == null) {
           throw new NotFoundException("Không tìm thấy chương với ID: " + chapterId);
       }

       //thong tin story
        Story story = storyRepository.findStoryById(chapterDTO.getStoryId());
        if(story == null) {
            throw new NotFoundException("Không tìm thấy truyện với ID: "+ chapterDTO.getStoryId());
        }

       chapterInfo.setTitle(chapterDTO.getTitle());
       chapterInfo.setStory(story);
       chapterInfo.setUpdatedAt(chapterDTO.getUpdatedAt());
       chapterInfo.setChapterNumber(chapterDTO.getChapterNumber());
       //cap nhat anh chapter
        if(chapterDTO.getImageFiles() != null && !chapterDTO.getImageFiles().isEmpty()){
            List<String> imagesPath = imageUtils.saveImages(chapterDTO.getImageFiles());
            chapterInfo.setImage_url(imagesPath);
        }
        chapterRepository.save(chapterInfo);

        return new ChapterDTO(
                chapterInfo.getId(),
                chapterInfo.getChapterNumber(),
                chapterInfo.getTitle(),
                chapterInfo.getStory().getId(),
                chapterInfo.getView_count(),
                chapterInfo.getIs_read(),
                chapterInfo.getImage_url(),
                chapterInfo.getCreatedAt(),
                chapterInfo.getUpdatedAt(),
                null
        );

    }

    //xoa chuong
//    public void deleteStory(Long storyId){
//        if(!storyRepository.existsById(storyId)) {
//            throw new NotFoundException("Không tìm thấy truyện với ID: "+ storyId);
//        }
//        storyRepository.deleteById(storyId);
//    }
    public void deleteChapter(Long chapterId){
        if (!chapterRepository.existsById(chapterId)) {
            throw new NotFoundException("Không tim thấy chương truyện với ID: "+ chapterId);
        }
        chapterRepository.deleteById(chapterId);
    }

    //list chapter theo id truyen
    public List<ChapterDTO> getChapterByStoryId(Long storyId){
        if(!storyRepository.existsById(storyId)) {
            throw new NotFoundException("Không tìm thấy truyện với ID: "+ storyId);
        }
        return chapterRepository.listChapterByStoryId(storyId);
    }

}
