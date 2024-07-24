package com.sekolah.app.service;

import com.sekolah.app.dto.LoginDto;
import com.sekolah.app.dto.ResponseDto;
import com.sekolah.app.dto.UserDto;
import com.sekolah.app.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserDto createUser(UserDto userDto);
    void deleteUserById(Long userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers(Map<String, Object> filters);
    ResponseEntity<ResponseDto> login(LoginDto loginDto);
    UserDto authenticate(String email, String password);
    UserDto addSubjectsToUser(Long userId, List<Long> subjectIds);
}
