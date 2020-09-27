package com.cg.hcms.appointment.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="DIAGNOSTIC_CENTER")
@DynamicUpdate(true)
@DynamicInsert(true)
public class DiagnosticCenterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "center_seq")  
	@SequenceGenerator(name ="center_seq",initialValue=1001, allocationSize = 1)
	private long centerId;
	
	@Column(name="CENTER_NAME",length=20)
	private String centerName;
	
	@Column(name="ADDRESS",length=40)
	private String address;
	
	@Column(name="CONTACT_NO",length=10)
	private String contactNo;
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = DiagnosticTestEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "centerId", referencedColumnName = "centerId")
	private List<DiagnosticTestEntity> test;


	public long getCenterId() {
		return centerId;
	}

	public void setCenterId(long centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}


	public List<DiagnosticTestEntity> getTest() {
		return test;
	}

	public void setTest(List<DiagnosticTestEntity> test) {
		this.test = test;
	}

	public DiagnosticCenterEntity(long centerId, String centerName, String address, String contactNo, 
			List<DiagnosticTestEntity> test) {
		super();
		this.centerId = centerId;
		this.centerName = centerName;
		this.address = address;
		this.contactNo = contactNo;
		this.test = test;
	}

	public DiagnosticCenterEntity() {
		super();
	}

	@Override
	public String toString() {
		return "DiagnosticCenter [centerId=" + centerId + ", centerName=" + centerName + ", address=" + address
				+ ", contactNo=" + contactNo + ", test=" + test + "]";
	}




	
	
	

}
