package com.sekolah.app.dto;

import com.sekolah.app.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String grade;
    private String major;
    private List<SubjectDto> subjects;

    public UserDto(User user) {
    }
}
