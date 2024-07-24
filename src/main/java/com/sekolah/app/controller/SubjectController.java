package com.sekolah.app.controller;

import com.sekolah.app.constants.HttpConstant;
import com.sekolah.app.dto.ResponseDto;
import com.sekolah.app.dto.SubjectDto;
import com.sekolah.app.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private SubjectService subjectService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addSubject(@RequestBody SubjectDto userDto){
        SubjectDto savedSubject = subjectService.addSubject(userDto);
        return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE,HttpConstant.SUCCESS_MESSAGE, savedSubject), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto> getSubjectById(@PathVariable Long id){
        SubjectDto user = subjectService.getSubjectById(id);
        if (user==null) {
            return new ResponseEntity<>(new ResponseDto(HttpConstant.NOT_FOUND_CODE,HttpConstant.NOT_FOUND_MESSAGE, ""), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE,HttpConstant.SUCCESS_MESSAGE, user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteSubjectById(@PathVariable Long id){
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE,HttpConstant.SUCCESS_MESSAGE, ""), HttpStatus.CREATED);
    }

    @PostMapping("/getListSubject")
    public ResponseEntity<ResponseDto> getListSubject(@RequestBody Map<String,Object> filters){
        List<SubjectDto> subjects = subjectService.getAllSubjects(filters);
        return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE, HttpConstant.SUCCESS_MESSAGE, subjects), HttpStatus.OK);
    }

}
