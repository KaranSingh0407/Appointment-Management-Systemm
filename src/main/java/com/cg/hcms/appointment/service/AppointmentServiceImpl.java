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
-Description              :     AppointmentServiceImpl class for business logic implements AppointmentService
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
			result.setCenter(source.getCenter());
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
			result.setCenter(source.getCenter());
		}
		return result;
	}
	
	/*******************************************************************************************************************************
	-Function Name            :     makeAppointment
	-Input Parameters         :     AppointmentModel Object
	-Return Type              :     appointmentModel object
	-Throws                   :     SlotNotAvailableException
	-Author                   :     Karan Singh Bisht
	-Created/Modified Date    :     23-09-2020
	-Description              :     adding appointment to the appointment database table 
	*******************************************************************************************************************************/

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
	
	/*******************************************************************************************************************************
	-Function Name            :     getAppointment
	-Input Parameters         :     BigInteger appointmentId 
	-Return Type              :     AppointmentModel Object
	-Throws                   :     AppointmentNotFoundException
	-Author                   :     Karan Singh Bisht
	-Created/Modified Date    :     23-09-2020
	-Description              :     getting appointment based on appointmentId from appointment database table
	*******************************************************************************************************************************/

	@Override
	public AppointmentModel getAppointment(BigInteger appointmentId) throws AppointmentNotFoundException{
		
		if (!appointmentRepo.existsById(appointmentId)) {
			throw new AppointmentNotFoundException("Appointment with id " + appointmentId + "not found");
		}
		return toModel(appointmentRepo.findById(appointmentId).get());
	}
	
	
	
	/*******************************************************************************************************************************
	-Function Name            :     getAllAppointments
	-Input Parameters         :     none
	-Return Type              :     list of AppointmentModel
	-Throws                   :     AppointmentNotFoundException
	-Author                   :     Karan Singh Bisht
	-Created/Modified Date    :     23-09-2020
	-Description              :     getting all the appointments from the appointment database table 
	*******************************************************************************************************************************/
	@Override
	public List<AppointmentModel> getAllAppointments() throws AppointmentNotFoundException{
		
		if (appointmentRepo.findAll().isEmpty()) {
			throw new AppointmentNotFoundException("Appointment list is empty");
		}
		return appointmentRepo.findAll().stream().map((entity)->toModel(entity)).collect(Collectors.toList());
		
	}
	
	/*******************************************************************************************************************************
	-Function Name            :     approveAppointment
	-Input Parameters         :     AppointmentModel Object and status boolean variable
	-Return Type              :     appointmentModel
	-Throws                   :     AppointmentAlreadyApprovedException
	-Author                   :     Karan Singh Bisht
	-Created/Modified Date    :     23-09-2020
	-Description              :     approves appointment and updates the appointment present in appointment database table
	*******************************************************************************************************************************/
	@Override
	public AppointmentModel approveAppointment(AppointmentModel appointment, boolean status) throws AppointmentAlreadyApprovedException{
		if (appointment.isApproved()) {
			throw new AppointmentAlreadyApprovedException(
					"Appointment with Id :" + appointment.getAppointmentId() + " is Already Approved");
		}
		
		appointment.setApproved(status);
		return toModel(appointmentRepo.save(of(appointment)));
		
	}
	
	/*******************************************************************************************************************************
	-Function Name            :     removeAppointmentById
	-Input Parameters         :     BigInteger appointmentId  
	-Return Type              :     boolean status
	-Throws                   :     AppointmentAlreadyApprovedException
	-Author                   :     Karan Singh Bisht
	-Created/Modified Date    :     23-09-2020
	-Description              :     deletes appointment that is stored under listOfAppointments in a Diagnostic center
	*******************************************************************************************************************************/
	@Override
	public boolean removeAppointmentById(BigInteger appointmentId) throws AppointmentNotFoundException{
		if(!appointmentRepo.existsById(appointmentId)) 
    		throw new AppointmentNotFoundException("Appointment with id: "+appointmentId+" not found");
		
		appointmentRepo.deleteById(appointmentId);
		return true;
	}
	

}
