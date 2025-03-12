package com.api.stormbook.service;

import com.api.stormbook.dto.ExamDTO;
import com.api.stormbook.entity.ExamEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService_2 {
    public List<ExamDTO> getUserExams();

    public ExamDTO getExamById(int id);

    public List<ExamDTO> searchExam(String keyword);
}
