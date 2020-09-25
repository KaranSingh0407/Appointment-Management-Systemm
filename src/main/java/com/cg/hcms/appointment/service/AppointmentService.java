package com.cg.hcms.appointment.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.hcms.appointment.entities.Appointment;

public interface AppointmentService {
	
	Appointment makeAppointment(Appointment appointment);
    Appointment getAppointment(BigInteger appointmentId);
	List<Appointment> getAllAppointments();
	Appointment approveAppointment(Appointment appointment,boolean status);
	boolean removeAppointmentById(BigInteger appointmentId);

}
