package com.api.stormbook.controller;


import com.api.stormbook.dto.ExamDTO;
import com.api.stormbook.entity.ExamEntity;
import com.api.stormbook.entity.response.ApiResponse;
import com.api.stormbook.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("/list-exam")
    public ResponseEntity<?> getListExams() {
        List<ExamDTO> exams = examService.getUserExams();
        ApiResponse<List<ExamDTO>> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công",exams);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-exam-id/{id}")
    public ResponseEntity<?> getExamById(@PathVariable int id) {
        ExamDTO result = examService.getExamById(id);
        ApiResponse<ExamDTO> response = new ApiResponse<>(HttpStatus.OK.value(), "Lấy dữ liệu thành công",result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchExam(@RequestParam(name = "keywork", required = false, defaultValue = "") String keyword) {
        List<ExamDTO> examDTOS = examService.searchExam(keyword);
        return ResponseEntity.ok(examDTOS);
    }

}
