package com.api.stormbook.service.ReportService;

import com.api.stormbook.dto.ReportDTO.ReportDTO;
import com.api.stormbook.entity.Chapter;
import com.api.stormbook.entity.Report;
import com.api.stormbook.entity.Story;
import com.api.stormbook.entity.User;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.AuthRepository.AuthUserRepository;
import com.api.stormbook.repository.ChapterRepository.ChapterRepository;
import com.api.stormbook.repository.ReportRepository.ReportRepository;
import com.api.stormbook.repository.StoryRepository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private ReportRepository reportRepository;

    public ReportDTO postReport(ReportDTO reportDTO) {
        Report report = new Report();
        //lay thong tin user
        User user = authUserRepository.findById(reportDTO.getUserId()).orElseThrow(() -> new NotFoundException("Không tìm thấy thông tin người dùng với ID: " + reportDTO.getUserId()));

        //lay thong tin truyen
        Story story = storyRepository.findStoryById(reportDTO.getStoryId());
        if(story == null) {
            throw new NotFoundException("Không tìm thấy truyện với ID: "+ reportDTO.getStoryId());
        }
        //lay thong tin chapter
        Chapter chapter = chapterRepository.findChapterById(reportDTO.getChapterId());
        if(chapter == null) {
            throw new NotFoundException("Không tìm thấy chương với ID: " + reportDTO.getChapterId());
        }

        report.setUser(user);
        report.setStory(story);
        report.setChapter(chapter);
        report.setType(reportDTO.getType());
        report.setContent(reportDTO.getContent());
        report.setStatus(reportDTO.getStatus());
        reportRepository.save(report);
        return new ReportDTO(
                report.getId(),
                report.getUser().getId(),
                report.getStory().getId(),
                report.getChapter().getId(),
                report.getType(),
                report.getContent(),
                report.getStatus(),
                report.getUpdatedAt(),
                report.getCreatedAt()
        );
    }

    //lay danh sach cua 1 nguoi dung
    public Page<ReportDTO> getListReportByUserId(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reportRepository.findListReportByUserId(userId, pageable);
    }

}
