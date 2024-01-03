package com.blogging.Model;

import lombok.Data;

@Data
public class ErrorDetail {
    
    private String timestamp;
    private String message;
    private String description;

    public ErrorDetail(String message, String description){
        this.message = message;
        this.description = description;
    }

}
