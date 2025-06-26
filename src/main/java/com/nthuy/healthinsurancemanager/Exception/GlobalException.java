//package com.nthuy.healthinsurancemanager.Exception;
//
//import com.nthuy.healthinsurancemanager.dto.response.RestResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.resource.NoResourceFoundException;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.nthuy.healthinsurancemanager.constant.EnumErrorCode.*;
//
//@RestControllerAdvice
//public class GlobalException {
//    @ExceptionHandler(value = UserNameExisted.class)
//    public ResponseEntity<RestResponse<Object>> handleUserNameInvalidEx(UserNameExisted ex) {
//        RestResponse<Object> restResponse = new RestResponse<Object>();
//        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        restResponse.setMessage(ex.getMessage());
//        restResponse.setErrorCode(VALIDATION_ERROR);
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
//    }
//    @ExceptionHandler(value = IdInvalidException.class)
//    public ResponseEntity<RestResponse<Object>> handleIdInvalidException(IdInvalidException ex) {
//        RestResponse<Object> restResponse = new RestResponse<Object>();
//        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        restResponse.setMessage(ex.getMessage());
//        restResponse.setErrorCode(VALIDATION_ERROR);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
//    }
//
//    @ExceptionHandler(value = {
//            UsernameNotFoundException.class,
//            BadCredentialsException.class
//    })
//    public ResponseEntity<RestResponse<Object>> handleIdException(Exception ex) {
//        RestResponse<Object> restResponse = new RestResponse<Object>();
//        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        restResponse.setMessage(ex.getMessage());
//        restResponse.setErrorCode(VALIDATION_ERROR);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
//    }
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<RestResponse<Object>> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
//        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
//        final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//        RestResponse<Object> res = new RestResponse<Object>();
//        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        res.setErrorCode(VALIDATION_ERROR);
//        List<String> errors = fieldErrors.stream().map(f->f.getDefaultMessage()).collect(Collectors.toList());
//        res.setMessage(errors.size() > 1 ? errors : errors.get(0));
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
//    }
//    @ExceptionHandler(value = {
//            NoResourceFoundException.class,
//    })
//    public ResponseEntity<RestResponse<Object>> handleNoResourceFoundException(NoResourceFoundException ex) {
//        RestResponse<Object> restResponse = new RestResponse<Object>();
//        restResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
//        restResponse.setMessage(ex.getMessage());
//        restResponse.setErrorCode(NOT_FOUND);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restResponse);
//    }
//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<RestResponse<Object>> handleIllegalStateException(Exception ex) {
//        RestResponse<Object> restResponse = new RestResponse<Object>();
//        restResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
//        restResponse.setMessage("có lỗi xảy ra");
//        restResponse.setErrorCode(INTERNAL_SERVER_ERROR);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restResponse);
//    }
//    @ExceptionHandler(value = BadRequestValidationException.class)
//    public ResponseEntity<RestResponse<Object>> handleBadRequestValidationException(BadRequestValidationException ex) {
//        RestResponse<Object> restResponse = new RestResponse<Object>();
//        restResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        restResponse.setMessage(ex.getMessage());
//        restResponse.setErrorCode(NOT_FOUND);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restResponse);
//    }
//
//
//}
