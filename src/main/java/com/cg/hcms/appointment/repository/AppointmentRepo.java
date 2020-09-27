package com.cg.hcms.appointment.repository;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hcms.appointment.entity.AppointmentEntity;
import com.cg.hcms.appointment.entity.DiagnosticTestEntity;

@Repository
public interface AppointmentRepo extends JpaRepository<AppointmentEntity, BigInteger>{
	
	public AppointmentEntity getAppointmentByDateTimeAndTest(LocalDateTime dateTime,DiagnosticTestEntity test);
}
