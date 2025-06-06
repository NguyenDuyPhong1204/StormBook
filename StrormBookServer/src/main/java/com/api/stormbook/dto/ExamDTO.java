package com.api.stormbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
//    private String password;
}
