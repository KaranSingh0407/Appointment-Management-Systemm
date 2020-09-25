package com.cg.hcms.appointment.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hcms.appointment.entities.Appointment;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, BigInteger>{
	
	public Appointment getAppointmentByDateTimeAndTestId(LocalDateTime dateTime,String testId);
}
