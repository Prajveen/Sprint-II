package com.capgemini.pecunia.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalHandlerExceptions {

	@ExceptionHandler(Account_NotFoundException.class) 
	public ResponseEntity<String> Account_NotFound(Account_NotFoundException e){ 
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND); 
	}
	@ExceptionHandler(Zero_balance_Exception.class) 
	public ResponseEntity<String>BalanceNill(Zero_balance_Exception e){ 
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND); 
	}
	@ExceptionHandler({Exception.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<RecordErrorResponse> handlerGenericException(Exception ex) {
		RecordErrorResponse errorResponse = new RecordErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"There is some technical issue!");
        return new ResponseEntity<RecordErrorResponse>(errorResponse, HttpStatus.OK);
	}
	
}
