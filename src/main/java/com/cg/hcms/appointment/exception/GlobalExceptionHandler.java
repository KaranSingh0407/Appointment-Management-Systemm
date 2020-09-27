package com.cg.hcms.appointment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<?> handleAppointmentNotFoundException(AppointmentNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
	@ExceptionHandler(SlotNotAvailableException.class)
	public ResponseEntity<?> handleSlotNotAvailableException(SlotNotAvailableException exception) {
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(exception.getMessage());
	}

	@ExceptionHandler(AppointmentAlreadyApprovedException.class)
	public ResponseEntity<?> handleAppointmentAlreadyApprovedException(AppointmentAlreadyApprovedException exception) {
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(exception.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleInvalidData(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());	
	}
	
}
