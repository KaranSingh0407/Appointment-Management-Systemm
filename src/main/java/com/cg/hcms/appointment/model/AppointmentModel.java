package com.cg.hcms.appointment.model;

import java.math.BigInteger;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.cg.hcms.appointment.entities.AppointmentEntity;

public class AppointmentModel {

private String userId;
	
	
	private BigInteger appointmentId;
	
	private String testId;
	
	@DateTimeFormat(pattern = "yyyy/MM/ddThh:mm")
	private LocalDateTime dateTime;
	
	private boolean approved = false;
	
	
	public AppointmentModel() {
		super();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigInteger getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(BigInteger appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
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

	public AppointmentModel(String userId, BigInteger appointmentId, String testId, LocalDateTime dateTime,
			boolean approved) {
		super();
		this.userId = userId;
		this.appointmentId = appointmentId;
		this.testId = testId;
		this.dateTime = dateTime;
		this.approved = approved;
	}
	
	public static AppointmentEntity toEntity(AppointmentModel source) {
		AppointmentEntity result = new AppointmentEntity();
		if(source!=null) {
			result.setAppointmentId(source.getAppointmentId());
			result.setApproved(source.isApproved());
			result.setDateTime(source.getDateTime());
			result.setTestId(source.getTestId());
			result.setUserId(source.getTestId());
		}
		return result;
	}
	
	public static AppointmentModel toModel(AppointmentEntity source) {
		AppointmentModel result = new AppointmentModel();
		if(source!=null) {
			result.setAppointmentId(source.getAppointmentId());
			result.setApproved(source.isApproved());
			result.setDateTime(source.getDateTime());
			result.setTestId(source.getTestId());
			result.setUserId(source.getTestId());
		}
		return result;
	}

	
}
