package com.cg.hcms.appointment.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hcms.appointment.entity.AppointmentEntity;
import com.cg.hcms.appointment.exception.AppointmentAlreadyApprovedException;
import com.cg.hcms.appointment.exception.AppointmentNotFoundException;
import com.cg.hcms.appointment.exception.SlotNotAvailableException;
import com.cg.hcms.appointment.model.AppointmentModel;
import com.cg.hcms.appointment.repository.AppointmentRepo;

/*******************************************************************************************************************************
-Author                   :     Karan Singh Bisht
-Created/Modified Date    :     23-09-2020
-Description              :     AppointmentService class for business logic
*******************************************************************************************************************************/


@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
	private AppointmentRepo appointmentRepo;
	
	private AppointmentEntity of(AppointmentModel source) {
		AppointmentEntity result = new AppointmentEntity();
		if(source!=null) {
			result.setAppointmentId(source.getAppointmentId());
			result.setApproved(source.isApproved());
			result.setDateTime(source.getDateTime());
			result.setTest(source.getTest());
			result.setUser(source.getUser());
		}
		return result;
	}
	
	private AppointmentModel toModel(AppointmentEntity source) {
		AppointmentModel result = new AppointmentModel();
		if(source!=null) {
			result.setAppointmentId(source.getAppointmentId());
			result.setApproved(source.isApproved());
			result.setDateTime(source.getDateTime());
			result.setTest(source.getTest());
			result.setUser(source.getUser());
		}
		return result;
	}

	@Override
	public AppointmentModel makeAppointment(AppointmentModel appointment) throws SlotNotAvailableException{
		
		LocalTime time=appointment.getDateTime().toLocalTime();
		
		if ((appointmentRepo.getAppointmentByDateTimeAndTest(appointment.getDateTime(), appointment.getTest())!=null)
			||appointment.getDateTime().isBefore(LocalDateTime.now().plusHours(3))||
			appointment.getDateTime().isAfter(LocalDateTime.now().plusDays(10))
			||time.isBefore(LocalTime.of(7, 59))||time.isAfter(LocalTime.of(20, 00))) {
					throw new SlotNotAvailableException("This slot is not available"); 
					}
	
		return toModel((appointmentRepo.save(of(appointment))));
	}

	@Override
	public AppointmentModel getAppointment(BigInteger appointmentId) {
		
		if (!appointmentRepo.existsById(appointmentId)) {
			throw new AppointmentNotFoundException("Appointment with id " + appointmentId + "not found");
		}
		return toModel(appointmentRepo.getOne(appointmentId));
	}

	@Override
	public List<AppointmentModel> getAllAppointments() {
		
		if (appointmentRepo.findAll().isEmpty()) {
			throw new AppointmentNotFoundException("Appointment list is empty");
		}
		return appointmentRepo.findAll().stream().map((entity)->toModel(entity)).collect(Collectors.toList());
		
	}

	@Override
	public AppointmentModel approveAppointment(AppointmentModel appointment, boolean status) {
		if (appointment.isApproved()) {
			throw new AppointmentAlreadyApprovedException(
					"Appointment with Id :" + appointment.getAppointmentId() + " is Already Approved");
		}

		appointment.setApproved(status);
		return toModel(appointmentRepo.save(of(appointment)));
		
	}

	@Override
	public boolean removeAppointmentById(BigInteger appointmentId) {
		if(!appointmentRepo.existsById(appointmentId)) 
    		throw new AppointmentNotFoundException("Appointment with id: "+appointmentId+" not found");
		
		appointmentRepo.deleteById(appointmentId);
		return true;
	}
	

}
