//package com.cg.hcms.appointment.service;
//
//import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.math.BigInteger;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.mockito.quality.Strictness;
//
//import com.cg.hcms.appointment.exception.AppointmentAlreadyApprovedException;
//import com.cg.hcms.appointment.exception.AppointmentNotFoundException;
//import com.cg.hcms.appointment.exception.SlotNotAvailableException;
//import com.cg.hcms.appointment.model.AppointmentModel;
//import com.cg.hcms.appointment.repository.AppointmentRepo;
//
///*******************************************************************************************************************************
//-Author                   :     Karan Singh Bisht
//-Created/Modified Date    :     23-09-2020
//-Description              :     AppointmentServiceTest class
//*******************************************************************************************************************************/
//
//@ExtendWith(MockitoExtension.class)
//@MockitoSettings(strictness = Strictness.LENIENT)
//public class AppointmentServiceTest {
//
//	@InjectMocks
//	AppointmentService appointmentServiceMock = new AppointmentServiceImpl();
//	
//	@Mock
//	AppointmentRepo appointmentRepoMock;
//	
//	private static AppointmentModel appointment;
//	private static AppointmentModel appointment1;
//	private static AppointmentModel appointment2;
//	private static AppointmentModel appointment3;
//	private static AppointmentModel appointmentSecond;
//	private static AppointmentModel appointmentThird;
//	private static List<AppointmentModel> appointmentList = new ArrayList<>();
//	
//	
//	@BeforeEach
//	public void init() {
//		
//		
//		
//		appointment = new AppointmentModel("121", BigInteger.valueOf(15), "23", LocalDateTime.of(2020,9 , 27	, 9, 30), false);
//		appointmentSecond = new AppointmentModel("120", BigInteger.valueOf(17), "22", LocalDateTime.of(2020,9 , 28, 9, 30), false);
//		appointmentThird = new AppointmentModel("122", BigInteger.valueOf(16), "24", LocalDateTime.of(2020,10 , 3, 9, 30), true);
//		appointment1=new AppointmentModel("230", BigInteger.valueOf(25), "13", LocalDateTime.of(2020, 10, 3, 5, 30), false);
//		appointment2=new AppointmentModel("231", BigInteger.valueOf(26) , "14", LocalDateTime.of(2020,10 , 15, 11, 30), false);
//		appointment3= new AppointmentModel("232", BigInteger.valueOf(5) , "15", LocalDateTime.now().plusHours(2), false);
//	
//	}
//	
//	
//	@Test()
//	public void slotNotAvailableExceptionTest() {
//		Mockito.when(appointmentRepoMock.save(AppointmentModel.toEntity(appointment1))).thenThrow(new SlotNotAvailableException("Error:Enter time between 08:00 to 17:00"));
//		Mockito.when(appointmentRepoMock.save(AppointmentModel.toEntity(appointment2))).thenThrow(new SlotNotAvailableException("Error:Cannot make appointment, choose a date closer to current date"));
//		Mockito.when(appointmentRepoMock.save(AppointmentModel.toEntity(appointment3))).thenThrow(new SlotNotAvailableException("Error:Cannot make appointment, choose a date closer to current date"));
//
//		assertThrows(SlotNotAvailableException.class, () -> {appointmentServiceMock.makeAppointment(appointment1);});
//		assertThrows(SlotNotAvailableException.class, () -> {appointmentServiceMock.makeAppointment(appointment2);});
//		assertThrows(SlotNotAvailableException.class, () -> {appointmentServiceMock.makeAppointment(appointment3);});
//	}
//	
//	
//	@Test
//	public void makeAppointmentTest() {
//		Mockito.when(appointmentRepoMock.save(AppointmentModel.toEntity(appointment))).thenReturn(AppointmentModel.toEntity(appointment));
//		assertEquals(appointment.getAppointmentId(), appointmentServiceMock.makeAppointment(appointment).getAppointmentId());
//	}
//	
//	@Test
//	public void appointmentNotFoundExceptionTest() {
//		Mockito.when(appointmentRepoMock.existsById(appointment.getAppointmentId())).thenThrow(new AppointmentNotFoundException("No Appointment Found"));
//	
//		assertThrows(AppointmentNotFoundException.class, () -> {appointmentServiceMock.removeAppointmentById(appointment.getAppointmentId());});
//	}
//	
//	@Test
//	public void getAllAppointments() {
//		
//		appointmentList.add(appointment);
//		appointmentList.add(appointmentSecond);
////		Mockito.when(appointmentRepoMock.findAll()).thenReturn(appointmentList);
//			
//		assertEquals(2, appointmentServiceMock.getAllAppointments().size());
//	}
//	
//	@Test
//	public void appointmentAlreadyApprovedExceptionTest() {
//		
//		assertThrows(AppointmentAlreadyApprovedException.class, () -> {appointmentServiceMock.approveAppointment(appointmentThird, true);});
//	}
//		
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
