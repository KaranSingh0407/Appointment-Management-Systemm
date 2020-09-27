package com.cg.hcms.appointment.service;

import static org.junit.Assert.assertThrows;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.cg.hcms.appointment.entity.DiagnosticCenterEntity;
import com.cg.hcms.appointment.entity.DiagnosticTestEntity;
import com.cg.hcms.appointment.entity.UserEntity;
import com.cg.hcms.appointment.exception.AppointmentAlreadyApprovedException;
import com.cg.hcms.appointment.exception.AppointmentNotFoundException;
import com.cg.hcms.appointment.exception.SlotNotAvailableException;
import com.cg.hcms.appointment.model.AppointmentModel;
import com.cg.hcms.appointment.repository.AppointmentRepo;

/*******************************************************************************************************************************
-Author                   :     Karan Singh Bisht
-Created/Modified Date    :     23-09-2020
-Description              :     AppointmentServiceTest class
*******************************************************************************************************************************/

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AppointmentServiceTest {

	@InjectMocks
	AppointmentService appointmentServiceMock = new AppointmentServiceImpl();
	
	@Mock
	AppointmentRepo appointmentRepoMock;
	
	private static AppointmentModel appointment;
	private static AppointmentModel appointment1;
	private static AppointmentModel appointment2;
	private static AppointmentModel appointment3;
	//private static AppointmentModel appointmentSecond;
	private static AppointmentModel appointmentThird;
	//private static List<AppointmentModel> appointmentList = new ArrayList<>();
	private static UserEntity user1;
	private static DiagnosticTestEntity test1;
	private static DiagnosticTestEntity test2;
	private static DiagnosticCenterEntity center1;
	private List<DiagnosticTestEntity> testList1;
	
	@BeforeClass
	public void initialize() {
		
		test1 = new DiagnosticTestEntity("COV123", "Covid test");
		test2 = new DiagnosticTestEntity("SUG123", "Blood Sugar test");
		testList1.add(test1);
		testList1.add(test2);
		
		center1 = new DiagnosticCenterEntity(1234, "Apollo Labs", "Dwarka Sect. 21", "9956131159", testList1);
		user1 = new UserEntity("123", "Karan123", "1234", BigInteger.valueOf(999999999), "abx@gmail.com", "User", 22, "M");
		
		
	}
	
	@BeforeEach
	public void init() {
		
		
		
		appointment = new AppointmentModel(user1, BigInteger.valueOf(17), test1, center1, LocalDateTime.of(2020,9 , 27, 9, 30), false);
		//appointmentSecond = new AppointmentModel(user1, BigInteger.valueOf(17), test2, center1, LocalDateTime.of(2020,9 , 28, 9, 30), false);
		//appointmentThird = new AppointmentModel(user1, BigInteger.valueOf(16), test1, center1, LocalDateTime.of(2020,10 , 3, 9, 30), true);
		appointment1=new AppointmentModel(user1, BigInteger.valueOf(25), test1, center1, LocalDateTime.of(2020, 10, 3, 5, 30), false);
		appointment2=new AppointmentModel(user1, BigInteger.valueOf(26) , test1, center1, LocalDateTime.of(2020,10 , 15, 11, 30), false);
		appointment3= new AppointmentModel(user1, BigInteger.valueOf(5) , test1, center1, LocalDateTime.now().plusHours(2), false);
	
	}
	
	
	@Test()
	public void slotNotAvailableExceptionTest() {
		Mockito.when(appointmentRepoMock.save(AppointmentModel.toEntity(appointment1))).thenThrow(new SlotNotAvailableException("Error:Enter time between 08:00 to 17:00"));
		Mockito.when(appointmentRepoMock.save(AppointmentModel.toEntity(appointment2))).thenThrow(new SlotNotAvailableException("Error:Cannot make appointment, choose a date closer to current date"));
		Mockito.when(appointmentRepoMock.save(AppointmentModel.toEntity(appointment3))).thenThrow(new SlotNotAvailableException("Error:Cannot make appointment, choose a date closer to current date"));

		assertThrows(SlotNotAvailableException.class, () -> {appointmentServiceMock.makeAppointment(appointment1);});
		assertThrows(SlotNotAvailableException.class, () -> {appointmentServiceMock.makeAppointment(appointment2);});
		assertThrows(SlotNotAvailableException.class, () -> {appointmentServiceMock.makeAppointment(appointment3);});
	}
	
	
//	@Test
//	public void makeAppointmentTest() {
//		Mockito.when(appointmentRepoMock.save(AppointmentModel.toEntity(appointment))).thenReturn(AppointmentModel.toEntity(appointment));
//		assertEquals(appointment.getAppointmentId(), appointmentServiceMock.makeAppointment(appointment).getAppointmentId());
//	}
	
	@Test
	public void appointmentNotFoundExceptionTest() {
		Mockito.when(appointmentRepoMock.existsById(appointment.getAppointmentId())).thenThrow(new AppointmentNotFoundException("No Appointment Found"));
	
		assertThrows(AppointmentNotFoundException.class, () -> {appointmentServiceMock.removeAppointmentById(appointment.getAppointmentId());});
	}
	
//	@Test
//	public void getAllAppointments() {
//		
//		appointmentList.add(appointment);
//		appointmentList.add(appointmentSecond);
//		Mockito.when(appointmentRepoMock.findAll()).thenReturn(appointmentList);
//			
//		assertEquals(2, appointmentServiceMock.getAllAppointments().size());
//	}
	
	@Test
	public void appointmentAlreadyApprovedExceptionTest() {
		
		assertThrows(AppointmentAlreadyApprovedException.class, () -> {appointmentServiceMock.approveAppointment(appointmentThird, true);});
	}
		
}




















