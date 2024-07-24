package com.sekolah.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sekolah.app.dto.SubjectDto;
import com.sekolah.app.entity.Subject;
import com.sekolah.app.repository.SubjectRepository;
import com.sekolah.app.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final ObjectMapper objectMapper;
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository, ObjectMapper objectMapper) {
        this.subjectRepository = subjectRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public SubjectDto addSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setSubjectName(subjectDto.getSubjectName());
        subject.setGrade(subjectDto.getGrade());
        subject.setMajor(subjectDto.getMajor());
        Subject savedSubject = subjectRepository.save(subject);
        return objectMapper.convertValue(savedSubject, SubjectDto.class);
    }

    @Override
    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    @Override
    public SubjectDto getSubjectById(Long id) {
        Optional<Subject> optSubject = subjectRepository.findById(id);
        if (optSubject.isPresent()) {
            Subject subject = optSubject.get();
            return objectMapper.convertValue(subject, SubjectDto.class); // Convert Entity to DTO
        }
        return null;
    }

    @Override
    public List<SubjectDto> getAllSubjects(Map<String, Object> filters) {
        List<Subject> subjects = subjectRepository.findByFilters(
                filters.get("subjectName") == null ? null : filters.get("subjectName").toString(),
                filters.get("major") == null ? null : filters.get("major").toString(),
                filters.get("grade") == null ? null : filters.get("grade").toString()
        );

        return subjects.stream()
                .map(subject -> objectMapper.convertValue(subject, SubjectDto.class))
                .collect(Collectors.toList());
    }
}
