package com.cg.hcms.appointment.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hcms.appointment.entities.Appointment;
import com.cg.hcms.appointment.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentService service;

	@PostMapping("/makeappointment")
	public Appointment makeAppointment(@RequestBody Appointment appointment) {
		return service.makeAppointment(appointment);
	}
	
	@GetMapping("/getappointment/{appointmentId}")
	public Appointment getAppointment(@PathVariable BigInteger appointmentId) {
		return service.getAppointment(appointmentId);
	}
	
	@GetMapping("/getallappointments")
	public List<Appointment> getAllAppointment() {
		return service.getAllAppointments();
	}
	
	@DeleteMapping("/removeappointment-centerid/{appointmentId}")
	public boolean removeAppointment(@PathVariable BigInteger appointmentId)
	{
		return  service.removeAppointmentById(appointmentId);
	}
	
	@PutMapping("/approveappointment/{appointmentId}/{status}")
	public Appointment approveAppoinment(@PathVariable BigInteger appointmentId, @PathVariable boolean status) {
		Appointment appointment = service.getAppointment(appointmentId);
		return service.approveAppointment(appointment, status);
	}
	
	
}
