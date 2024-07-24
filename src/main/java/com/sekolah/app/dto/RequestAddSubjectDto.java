package com.sekolah.app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestAddSubjectDto {
    private Long userId;
    private List<Long> subjectIds;
}
