package com.cg.hcms.appointment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<?> handleAppointmentNotFoundException(AppointmentNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		//To handle Appointment not found exception
	}
	
	@ExceptionHandler(SlotNotAvailableException.class)
	public ResponseEntity<?> handleSlotNotAvailableException(SlotNotAvailableException exception) {
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(exception.getMessage());
		//To handle slot not available exception
	}

	@ExceptionHandler(AppointmentAlreadyApprovedException.class)
	@ResponseStatus(value = HttpStatus.ALREADY_REPORTED, reason = "Appointment Already Approved")
	public ResponseEntity<?> handleAppointmentAlreadyApprovedException(AppointmentAlreadyApprovedException exception) {
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(exception.getMessage());
		//To handle Appointment already approved exception
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You have entered invalid data", code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleInvalidData(Exception exception) {
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(exception.getMessage());	
		//To handle bad requests
	}
	
}
