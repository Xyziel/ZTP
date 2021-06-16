package ztp.shelter.exceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.NoResultException;

//@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request)
    {
        return ResponseEntity.status(404).body(new ExceptionResponse(ex.getMessage(), 404));
    }





    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> customHandler(Exception exception)
    {
        return ResponseEntity.status(500).body(new ExceptionResponse(exception.getMessage(), 500));
    }


    @ExceptionHandler(value = SentDataIsNullException.class)
    public ResponseEntity<?> sentDataIsNullHandler(SentDataIsNullException exception)
    {
        return ResponseEntity.status(exception.getHttpStatus()).body(new ExceptionResponse(exception.getMessage(),
                                                                                           exception.getHttpStatus()));
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<?> customHandler(CustomException exception)
    {
        return ResponseEntity.status(exception.getHttpStatus()).body(new ExceptionResponse(exception.getMessage(),
                                                                                           exception.getHttpStatus()));
    }


    @ExceptionHandler(value = DataAlreadyExistsException.class)
    public ResponseEntity<?> dataAlreadyExistHandler(DataAlreadyExistsException exception)
    {
        return ResponseEntity.status(exception.getHttpStatus()).body(new ExceptionResponse(exception.getMessage(),
                                                                                           exception.getHttpStatus()));
    }


    @ExceptionHandler(value = DataDoesNotExistException.class)
    public ResponseEntity<?> dataDoesNotExistHandler(DataDoesNotExistException exception)
    {
        return ResponseEntity.status(exception.getHttpStatus()).body(new ExceptionResponse(exception.getMessage(),
                                                                                           exception.getHttpStatus()));
    }


    //OR NOT_FOUND
    @ExceptionHandler(value = JWTTokenViolatedException.class)
    public ResponseEntity<?> jwtTokenViolatedHandler(JWTTokenViolatedException exception)
    {
        return ResponseEntity.status(exception.getHttpStatus()).body(new ExceptionResponse(exception.getMessage(),
                                                                                           exception.getHttpStatus()));
    }


    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException exception)
    {
        return ResponseEntity.status(422).body(new ExceptionResponse("Wrong format for path variable", 422));
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request)
    {
        return ResponseEntity.status(422).body(new ExceptionResponse("Wrong format for sent data or empty", 422));
    }
}
