package com.cg.hcms.appointment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_info")
public class DiagnosticTestEntity {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "test_id")
    private String testId;
	@Column(name = "test_name")
    private String testName;
	
	public DiagnosticTestEntity() {
		super();
	}
	
	public DiagnosticTestEntity(String testId, String testName) {
		super();
		this.testName = testName;
		this.testId = testId;
	}
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	@Override
	public String toString() {
		return "DiagnosticTest [testId=" + testId + ", testName=" + testName + "]";
	}

}
