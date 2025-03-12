package com.api.stormbook.mapper;

import com.api.stormbook.dto.ExamDTO;
import com.api.stormbook.entity.ExamEntity;

public class ExamMapper {
    public static ExamDTO toExamDTO(ExamEntity exam){
        ExamDTO examDTO = new ExamDTO();
        examDTO.setId(exam.getId());
        examDTO.setName(exam.getName());
        examDTO.setPhone(exam.getPhone());
        examDTO.setAddress(exam.getAddress());
//        examDTO.setPassword(exam.getPassword());
        return examDTO;
    }
}
