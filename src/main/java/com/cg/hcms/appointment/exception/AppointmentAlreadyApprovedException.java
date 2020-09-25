package com.cg.hcms.appointment.exception;

public class AppointmentAlreadyApprovedException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppointmentAlreadyApprovedException(String message)
	{
		super(message);
	}
}
