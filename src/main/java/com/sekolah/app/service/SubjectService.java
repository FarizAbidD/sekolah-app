package com.sekolah.app.service;

import com.sekolah.app.dto.SubjectDto;

import java.util.List;
import java.util.Map;

public interface SubjectService {
    SubjectDto addSubject(SubjectDto subjectDto);
    void deleteSubject(Long userDto);
    SubjectDto getSubjectById(Long id);
    List<SubjectDto> getAllSubjects(Map<String, Object> filters);
}
