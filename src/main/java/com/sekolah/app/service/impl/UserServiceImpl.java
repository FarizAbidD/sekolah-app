package com.sekolah.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sekolah.app.constants.HttpConstant;
import com.sekolah.app.dto.LoginDto;
import com.sekolah.app.dto.ResponseDto;
import com.sekolah.app.dto.UserDto;
import com.sekolah.app.entity.Subject;
import com.sekolah.app.entity.User;
import com.sekolah.app.repository.SubjectRepository;
import com.sekolah.app.repository.UserRepository;
import com.sekolah.app.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    ObjectMapper om = new ObjectMapper();
    @PersistenceContext
    private EntityManager entityManager;

    private UserRepository userRepository;
    private SubjectRepository subjectRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setGrade(userDto.getGrade());
        user.setMajor(userDto.getMajor());

        if (userDto.getSubjects() != null) {
            List<Subject> subjects = userDto.getSubjects().stream().map(subjectDto -> {
                Subject subject = subjectRepository.findById(subjectDto.getId()).orElse(null);
                return subject;
            }).collect(Collectors.toList());
            user.setSubjects(subjects);
        }
        User savedUser = userRepository.save(user);
        return om.convertValue(savedUser, UserDto.class);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return om.convertValue(user, UserDto.class); // Convert Entity to DTO
        }
        return null;
    }

    @Override
    public List<UserDto> getAllUsers(Map<String, Object> filters) {
        List<User> users = userRepository.findByFilters(
                filters.get("name") == null ? null : filters.get("name").toString(),
                filters.get("email") == null ? null : filters.get("email").toString(),
                filters.get("major") == null ? null : filters.get("major").toString(),
                filters.get("grade") == null ? null : filters.get("grade").toString()
        );

        return users.stream()
                .map(user -> om.convertValue(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<ResponseDto> login(LoginDto loginDto) {
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        //find user by email
        Optional<User> userOpt = userRepository.findByFilters(null,email,null,null).stream().findFirst();
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) { // In real applications, use hashed password
                return new ResponseEntity<>(new ResponseDto(HttpConstant.SUCCESS_CODE, HttpConstant.SUCCESS_MESSAGE, om.convertValue(user, UserDto.class)), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseDto(HttpConstant.ERROR_CODE, HttpConstant.ERROR_MESSAGE, "Password Wrong"), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ResponseDto(HttpConstant.ERROR_CODE, HttpConstant.ERROR_MESSAGE, "Account Not Found"), HttpStatus.BAD_REQUEST);

    }

    @Override
    public UserDto authenticate(String email, String password) {
        User user = userRepository.findByEmailAndPassword(email, password);
        return user != null ? om.convertValue(user, UserDto.class) : null;
    }

    @Override
    public UserDto addSubjectsToUser(Long userId, List<Long> subjectIds) {
        Optional<User> userOpt = userRepository.findById(userId);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<Subject> subjects = subjectRepository.findAllById(subjectIds);
            // Validation to prevent adding duplicate subjects
            for (Subject subject : subjects) {
                if (!user.getSubjects().contains(subject)) {
                    user.getSubjects().add(subject);
                }
            }
            user = userRepository.save(user);
            return om.convertValue(user, UserDto.class);
        }

        return null;
    }
}
