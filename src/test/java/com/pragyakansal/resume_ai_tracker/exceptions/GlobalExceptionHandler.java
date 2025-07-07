package com.pragyakansal.resume_ai_tracker.exceptions;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import com.pragyakansal.resume_ai_tracker.dtos.ErrorDataHolderDto;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ErrorDataHolderDto getErrorHolder(Exception exception, HttpServletRequest request, HttpStatus status) {
        ErrorDataHolderDto errorHolder = new ErrorDataHolderDto(
                exception.getMessage(),
                LocalDateTime.now().toString(),
                request.getRequestURI(),
                exception.getClass().getSimpleName(),
                null
        );
        errorHolder.setStatus(status.value());
        return errorHolder;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDataHolderDto> handleNotFoundException(NotFoundException exception, HttpServletRequest request) {
        ErrorDataHolderDto errorHolder = getErrorHolder(exception, request, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorHolder, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadParameterException.class)
    public ResponseEntity<ErrorDataHolderDto> handleBadParameterException(BadParameterException exception, HttpServletRequest request) {
        ErrorDataHolderDto errorHolder = getErrorHolder(exception, request, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorHolder, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorDataHolderDto> handleAlreadyExistsException(AlreadyExistsException exception, HttpServletRequest request) {
        ErrorDataHolderDto errorHolder = getErrorHolder(exception, request, HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorHolder, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDataHolderDto> handleGenericException(Exception exception, HttpServletRequest request) {
        ErrorDataHolderDto errorHolder = getErrorHolder(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorHolder, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
