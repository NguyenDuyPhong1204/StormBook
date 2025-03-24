package com.api.stormbook.controller.ReportController;

import com.api.stormbook.dto.CommentDTO.CommentDTO;
import com.api.stormbook.dto.ReportDTO.ReportDTO;
import com.api.stormbook.dto.ReportDTO.ReportResponseDTO;
import com.api.stormbook.entity.Report;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.repository.ReportRepository.ReportRepository;
import com.api.stormbook.service.ReportService.ReportService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private ReportRepository reportRepository;

    //tao report
    @PostMapping("/create")
    public ResponseEntity<?> createReport(@RequestBody ReportDTO report) {
        try{
            ReportDTO reportDTO = reportService.postReport(report);
            ApiResponse<ReportDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Báo cáo thành công!", reportDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ErrorException("Lỗi comment!" + e.getMessage());
        }
    }

    //lay chi tiet 1 report
    @GetMapping("/{reportId}")
    public ResponseEntity<?> getReport(@PathVariable Long reportId) {
        ReportResponseDTO result = reportRepository.findDetailsReportById(reportId);
        if(result == null){
            throw new NotFoundException("Không tìm thấy báo cáo với ID: "+ reportId);
        }
        ApiResponse<ReportResponseDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy thông tin báo cáo thành công!", result);
        return ResponseEntity.ok(response);
    }

    //list report theo userId
    @GetMapping("/list-by-userId/{userId}")
    public ResponseEntity<?> getReportByUserId(
            @PathVariable Long userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size)  {
        try{
            Page<ReportDTO> listReport = reportService.getListReportByUserId(userId, page, size);
            if(listReport.isEmpty()){
                throw new NotFoundException("Không có báo cáo nào!");
            }
            ApiResponse<List<ReportDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách thành công!", listReport.getContent());
            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw new ErrorException("Lỗi lấy danh sách!" + e.getMessage());
        }
    }

}
