package com.api.stormbook.repository.ReportRepository;

import com.api.stormbook.dto.ReportDTO.ReportDTO;
import com.api.stormbook.dto.ReportDTO.ReportResponseDTO;
import com.api.stormbook.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    //lay thong tin chi tiet cua report
    @Query(
            "select new com.api.stormbook.dto.ReportDTO.ReportResponseDTO" +
                    "(r.id,r.user.id, r.story.id,r.chapter.id, r.type," +
                    " r.content, r.status, r.user.fullName, r.user.avatar, " +
                    "r.story.title, r.story.cover_image,r.chapter.chapterNumber, " +
                    "r.createdAt,r.updatedAt) " +
                    "from Report r " +
                    "join r.user u " +
                    "join r.story s " +
                    "join r.chapter ch where r.id=:reportId"
    )
    ReportResponseDTO findDetailsReportById(Long reportId);

    //lay list report cua 1 nguoi
    @Query(
            "select new com.api.stormbook.dto.ReportDTO.ReportDTO" +
                    "(r.id, r.user.id, r.story.id, r.chapter.id," +
                    " r.type, r.content, r.status, r.createdAt,r.updatedAt) " +
                    "from Report r " +
                    "join r.user u " +
                    "join r.story s " +
                    "join r.chapter ch " +
                    "where r.user.id =:userId"
    )
    Page<ReportDTO> findListReportByUserId(Long userId, Pageable pageable);


    //lay tat ca report
    @Query(
            "select new com.api.stormbook.dto.ReportDTO.ReportDTO" +
                    "(r.id, r.user.id, r.story.id, r.chapter.id," +
                    " r.type, r.content, r.status, r.createdAt,r.updatedAt) " +
                    "from Report r " +
                    "join r.user u " +
                    "join r.story s " +
                    "join r.chapter ch ")
    Page<ReportDTO> findAllReport(Pageable pageable);



}
