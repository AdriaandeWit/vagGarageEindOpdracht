package nl.novi.Eindopdracht.Controllers;

import nl.novi.Eindopdracht.Exceptions.*;
import nl.novi.Eindopdracht.Exceptions.IndexOutOfBoundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<Object> exception(RecordNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }
        @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> exception(UsernameNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }
        @ExceptionHandler(value = InspectionNotFoundException.class)
    public ResponseEntity<Object> exception(InspectionNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(value = EnumNotFoundException.class)
    public ResponseEntity<Object> exception(EnumNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(value = CarStatusNotFoundException.class)
    public ResponseEntity<Object> exception(CarStatusNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(value = CarRepairNotFoundException.class)
    public ResponseEntity<Object> exception(CarRepairNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(value = CarNotFoundException.class)
    public ResponseEntity<Object> exception(CarNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }
        @ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<Object> exception(AccountNotFoundException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }

        @ExceptionHandler(value = IndexOutOfBoundsException.class)
    public ResponseEntity<Object> exception(IndexOutOfBoundsException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> exception(BadRequestException exception) {

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);

    }






}
