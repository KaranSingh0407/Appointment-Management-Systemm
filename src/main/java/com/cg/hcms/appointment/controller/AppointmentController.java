package com.cg.hcms.appointment.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hcms.appointment.model.AppointmentModel;
import com.cg.hcms.appointment.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	AppointmentService service;

	@PostMapping("/makeappointment")
	public ResponseEntity<?> makeAppointment(@RequestBody AppointmentModel appointment) {
		return ResponseEntity.ok(service.makeAppointment(appointment));
	}
	
	@GetMapping("/getappointment/{appointmentId}")
	public ResponseEntity<?> getAppointment(@PathVariable BigInteger appointmentId) {
		return ResponseEntity.ok(service.getAppointment(appointmentId));
	}
	
	@GetMapping("/getallappointments")
	public ResponseEntity<?> getAllAppointment() {
		return ResponseEntity.ok(service.getAllAppointments());
	}
	
	@DeleteMapping("/removeappointment-centerid/{appointmentId}")
	public ResponseEntity<?> removeAppointment(@PathVariable BigInteger appointmentId)
	{
		return  ResponseEntity.ok(service.removeAppointmentById(appointmentId));
	}
	
	@PutMapping("/approveappointment/{appointmentId}/{status}")
	public ResponseEntity<?> approveAppoinment(@PathVariable BigInteger appointmentId, @PathVariable boolean status) {
		AppointmentModel appointment = service.getAppointment(appointmentId);
		return ResponseEntity.ok(service.approveAppointment(appointment, status));
	}
	
	
}
