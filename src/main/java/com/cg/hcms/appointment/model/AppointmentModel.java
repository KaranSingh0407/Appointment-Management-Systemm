package com.cg.hcms.appointment.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.cg.hcms.appointment.entity.AppointmentEntity;
import com.cg.hcms.appointment.entity.DiagnosticCenterEntity;
import com.cg.hcms.appointment.entity.DiagnosticTestEntity;
import com.cg.hcms.appointment.entity.UserEntity;

/*******************************************************************************************************************************
-Author                   :     Karan Singh Bisht
-Created/Modified Date    :     23-09-2020
-Description              :     AppointmentModel class
*******************************************************************************************************************************/

public class AppointmentModel {

	private UserEntity user;
	
	private BigInteger appointmentId;
	
	private DiagnosticTestEntity test;
	
	private DiagnosticCenterEntity center;
	
	@DateTimeFormat(pattern = "yyyy/MM/ddThh:mm")
	private LocalDateTime dateTime;
	
	private boolean approved = false;
	
	
	
	
	public DiagnosticCenterEntity getCenter() {
		return center;
	}


	public void setCenter(DiagnosticCenterEntity center) {
		this.center = center;
	}


	public AppointmentModel() {
		super();
	}
	
	
	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}


	public BigInteger getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(BigInteger appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public DiagnosticTestEntity getTest() {
		return test;
	}


	public void setTest(DiagnosticTestEntity test) {
		this.test = test;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}



	public AppointmentModel(UserEntity user, BigInteger appointmentId, DiagnosticTestEntity test,
			DiagnosticCenterEntity center, LocalDateTime dateTime, boolean approved) {
		super();
		this.user = user;
		this.appointmentId = appointmentId;
		this.test = test;
		this.center = center;
		this.dateTime = dateTime;
		this.approved = approved;
	}


	public static AppointmentEntity toEntity(AppointmentModel source) {
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
	
	public static AppointmentModel toModel(AppointmentEntity source) {
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

	
}
