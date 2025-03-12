package com.api.stormbook.service;

import com.api.stormbook.dto.ExamDTO;
import com.api.stormbook.entity.ExamEntity;
import com.api.stormbook.exception.NotFoundException;
import com.api.stormbook.mapper.ExamMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExamService implements ExamService_2 {
    private static ArrayList<ExamEntity> exam = new ArrayList<ExamEntity>();

    static{
        exam.add(new ExamEntity(1, "Phong-Baox-To", "phong1204@gmail.com", "0123456789", "Yen Bai","Phong1204"));
        exam.add(new ExamEntity(2, "Phong-Baox-To", "phong1204@gmail.com", "0123456789", "Yen Bai","Phong1204"));
    }

    @Override
    public List<ExamDTO> getUserExams() {
        List<ExamDTO> result = new ArrayList<ExamDTO>();
        for(ExamEntity examEntity: exam){
            result.add(ExamMapper.toExamDTO(examEntity));
        }
        if(!result.isEmpty()){
            return result;
        }
        throw new NotFoundException("Không có danh sách");
    }

    @Override
    public ExamDTO getExamById(int id) {
        for(ExamEntity examEntity: exam){
            if(examEntity.getId() == id){
                return ExamMapper.toExamDTO(examEntity);
            }
        }
        throw new NotFoundException("Exam not found");
    }

    @Override
    public List<ExamDTO> searchExam(String keyword) {
        List<ExamDTO> result = new ArrayList<>();
        for(ExamEntity examEntity: exam){
            if (examEntity.getName().contains(keyword)) {
                result.add(ExamMapper.toExamDTO(examEntity));
            }
        }
        return result;
    }


}
