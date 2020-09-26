package com.cg.hcms.appointment.service;

import java.math.BigInteger;
import java.util.List;

import com.cg.hcms.appointment.model.AppointmentModel;

public interface AppointmentService {
	
	AppointmentModel makeAppointment(AppointmentModel appointment);
    AppointmentModel getAppointment(BigInteger appointmentId);
	List<AppointmentModel> getAllAppointments();
	AppointmentModel approveAppointment(AppointmentModel appointment,boolean status);
	boolean removeAppointmentById(BigInteger appointmentId);

}
