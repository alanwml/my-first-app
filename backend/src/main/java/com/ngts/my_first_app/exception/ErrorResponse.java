package com.ngts.my_first_app.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@ToString
public class ErrorResponse {
    private int status;
    private List<String> message;
    private LocalDateTime timestamp;
}
