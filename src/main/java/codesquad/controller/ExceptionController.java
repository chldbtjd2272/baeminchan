package codesquad.controller;

import codesquad.exception.AlreadyExistsUserException;
import codesquad.exception.LoginFailedException;
import codesquad.util.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.h2.jdbc.JdbcSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(LoginFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse loginFailed(LoginFailedException e) {
        log.debug("custom error message : {}", e.getMessage());
        return new CustomResponse(CustomResponse.ERROR_MSG.LOGIN_FAILED_ERROR, null);
    }

    @ExceptionHandler(AlreadyExistsUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomResponse sqlException(AlreadyExistsUserException e) {
        log.debug("sql exception message : {}", e.getMessage());
        return new CustomResponse(CustomResponse.ERROR_MSG.ALREADT_EXISTS_USER_ERROR, null);

    }

}
