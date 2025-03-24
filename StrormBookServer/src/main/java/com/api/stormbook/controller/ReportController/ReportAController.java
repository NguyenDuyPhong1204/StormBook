package com.api.stormbook.controller.ReportController;

import com.api.stormbook.dto.ReportDTO.ReportDTO;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.exception.ErrorException;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.service.ReportService.ReportAService;
import com.api.stormbook.service.ReportService.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/a/report")
public class ReportAController {
    @Autowired
    private ReportAService reportService;

    @GetMapping("/all-report")
    public ResponseEntity<?> getAllReports(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
         try {
             Page<ReportDTO> listReport = reportService.getAllReports(page, size);
             if(listReport.isEmpty()){
                 throw new NotFoundException("Không có báo cáo nào!");
             }
             ApiResponse<List<ReportDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy danh sách thành công!", listReport.getContent());
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             throw new ErrorException("Lỗi lấy danh sách!" + e.getMessage());
         }
    }

    @PutMapping("/{reportId}")
    public ResponseEntity<?> updateReport(@PathVariable Long reportId, @RequestBody ReportDTO reportDTO) {
        try {
            ReportDTO report = reportService.updateReport(reportId, reportDTO);
            ApiResponse<ReportDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Xác nhận báo cáo đã được xử lý!", report);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            throw new ErrorException("Lỗi chỉnh sửa báo cáo!" + e.getMessage());
        }
    }

}
