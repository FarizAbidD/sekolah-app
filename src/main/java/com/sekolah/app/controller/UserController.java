package com.sekolah.app.controller;

import com.sekolah.app.constants.HttpConstant;
import com.sekolah.app.dto.LoginDto;
import com.sekolah.app.dto.RequestAddSubjectDto;
import com.sekolah.app.dto.ResponseDto;
import com.sekolah.app.dto.UserDto;
import com.sekolah.app.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE,HttpConstant.SUCCESS_MESSAGE, savedUser), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDto> getUserById(@PathVariable Long id){
        UserDto user = userService.getUserById(id);
        if (user==null) {
            return new ResponseEntity<>(new ResponseDto(HttpConstant.NOT_FOUND_CODE,HttpConstant.NOT_FOUND_MESSAGE, ""), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE,HttpConstant.SUCCESS_MESSAGE, user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE,HttpConstant.SUCCESS_MESSAGE, ""), HttpStatus.CREATED);
    }

    @PostMapping("/getListUser")
    public ResponseEntity<ResponseDto> getListUser(@RequestBody Map<String,Object> filters){
        List<UserDto> users = userService.getAllUsers(filters);
        return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE, HttpConstant.SUCCESS_MESSAGE, users), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody LoginDto loginDto){
        return userService.login(loginDto);
    }

    @PostMapping("/addSubjects")
    public ResponseEntity<ResponseDto> addSubjectsToUser(@RequestBody RequestAddSubjectDto request) {
        UserDto updatedUser = userService.addSubjectsToUser(request.getUserId(), request.getSubjectIds());
        if (updatedUser != null) {
            return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE,HttpConstant.SUCCESS_MESSAGE, updatedUser), HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
