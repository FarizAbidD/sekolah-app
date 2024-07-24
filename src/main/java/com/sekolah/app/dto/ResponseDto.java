package com.sekolah.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
    private String status;
    private String messege;
    private Object data;

    public ResponseDto(String status, String messege, Object data) {
        this.status = status;
        this.messege = messege;
        this.data = data;
    }
}
