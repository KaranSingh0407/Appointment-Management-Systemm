package com.cg.hcms.appointment.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.hcms.appointment.entities.Appointment;
import com.cg.hcms.appointment.exception.AppointmentAlreadyApprovedException;
import com.cg.hcms.appointment.exception.AppointmentNotFoundException;
import com.cg.hcms.appointment.exception.SlotNotAvailableException;
import com.cg.hcms.appointment.repository.AppointmentRepo;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	private AppointmentRepo appointmentRepo;

	@Override
	public Appointment makeAppointment(Appointment appointment) {
		
		LocalTime time=appointment.getDateTime().toLocalTime();

	    if (appointment.getDateTime().isBefore(LocalDateTime.now().plusHours(3))||appointment.getDateTime().isAfter(LocalDateTime.now().plusDays(10))
	    	||time.isBefore(LocalTime.of(7, 59))||time.isAfter(LocalTime.of(20, 00))) 
	    {
			throw new SlotNotAvailableException("This slot is not available");
		}
		return appointmentRepo.save(appointment);
	}

	@Override
	public Appointment getAppointment(BigInteger appointmentId) {
		
		if (!appointmentRepo.existsById(appointmentId)) {
			throw new AppointmentNotFoundException("Appointment with id " + appointmentId + "not found");
		}
		return appointmentRepo.getOne(appointmentId);
	}

	@Override
	public List<Appointment> getAllAppointments() {
		
		if (appointmentRepo.findAll().isEmpty()) {
			throw new AppointmentNotFoundException("Appointment list is empty");
		}
		return appointmentRepo.findAll();
		
	}

	@Override
	public Appointment approveAppointment(Appointment appointment, boolean status) {
		if (appointment.isApproved()) {
			throw new AppointmentAlreadyApprovedException(
					"Appointment with Id :" + appointment.getAppointmentId() + " is Already Approved");
		}

		appointment.setApproved(status);
		return appointmentRepo.save(appointment);
		
	}

	@Override
	public boolean removeAppointmentById(BigInteger appointmentId) {
		if(!appointmentRepo.existsById(appointmentId)) 
    		throw new AppointmentNotFoundException("Appointment with id: "+appointmentId+" not found");
		
		appointmentRepo.deleteById(appointmentId);
		return true;
	}
	

}
