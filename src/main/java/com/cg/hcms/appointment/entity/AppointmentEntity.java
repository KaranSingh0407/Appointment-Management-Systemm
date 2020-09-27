package com.cg.hcms.appointment.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/*******************************************************************************************************************************
-Author                   :     Karan Singh Bisht
-Created/Modified Date    :     23-09-2020
-Description              :     AppointmentEntity class
*******************************************************************************************************************************/

@Entity
@Table(name="appointment_info")
@SequenceGenerator(name="seq", initialValue=10000)
public class AppointmentEntity {
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "userId", nullable = false)
	private UserEntity user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "centerId", nullable = false)
	private DiagnosticCenterEntity center;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq")
	private BigInteger appointmentId;
	
	@OneToOne
	private DiagnosticTestEntity test;
	
	@DateTimeFormat(pattern = "yyyy/MM/ddThh:mm")
	private LocalDateTime dateTime;
	private boolean approved = false;
	
	
	
	
	public DiagnosticCenterEntity getCenter() {
		return center;
	}


	public void setCenter(DiagnosticCenterEntity center) {
		this.center = center;
	}


	public AppointmentEntity() {
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
	

	public DiagnosticTestEntity getTest() {
		return test;
	}

	public void setTest(DiagnosticTestEntity test) {
		this.test = test;
	}


	public AppointmentEntity(UserEntity user, DiagnosticCenterEntity center, BigInteger appointmentId,
			DiagnosticTestEntity test, LocalDateTime dateTime, boolean approved) {
		super();
		this.user = user;
		this.center = center;
		this.appointmentId = appointmentId;
		this.test = test;
		this.dateTime = dateTime;
		this.approved = approved;
	}


	

	
	
	
	
	
}
