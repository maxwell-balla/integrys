package com.integrys.backend.exceptions;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApiResponse {

    private Integer status;
    private Boolean success;
    private Date timestamp;
    private String message;
    private String details;
    private Object data;
    private List<ValidationError> errors;

    public ApiResponse(Integer status, Boolean success , Date timestamp, String message, String details) {
        super();
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.success=success;
    }

    public ApiResponse(Integer status, Date timestamp, String message) {
        super();
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
    }

    public ApiResponse(String message, Object data) {
        this.status = 200;
        this.timestamp = new Date();
        this.message = message;
        this.data = data;
        this.success= true;
    }

    public ApiResponse(String message) {
        this.status = 200;
        this.timestamp = new Date();
        this.message = message;
        this.data = null;
        this.success = true;
    }

    public ApiResponse(Integer status, String message) {
        this.status = status;
        this.timestamp = new Date();
        this.message = message;
        this.data = null;
        this.success = true;
    }


    public ApiResponse(Integer status, Boolean success, String message, String details, List<ValidationError> errors) {
        super();
        this.status = status;
        this.timestamp = new Date();
        this.message = message;
        this.errors = errors;
        this.success = success;
        this.details = details;
    }

}