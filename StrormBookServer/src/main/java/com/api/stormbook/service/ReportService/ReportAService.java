package com.api.stormbook.service.ReportService;

import com.api.stormbook.dto.ReportDTO.ReportDTO;
import com.api.stormbook.entity.Report;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.ReportRepository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportAService {
    @Autowired
    ReportRepository reportRepository;

    public Page<ReportDTO> getAllReports(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return reportRepository.findAllReport(pageable);
    }

    public ReportDTO updateReport(Long reportId, ReportDTO reportDTO) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException("Không có report với ID: " + reportId));
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

}
