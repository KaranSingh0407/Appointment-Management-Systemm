package com.cg.hcms.appointment.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.cg.hcms.appointment.entities.Appointment;
import com.cg.hcms.appointment.exception.SlotNotAvailableException;
import com.cg.hcms.appointment.repository.AppointmentRepo;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AppointmentServiceTest {

	@InjectMocks
	AppointmentService appointmentServiceMock = new AppointmentServiceImpl();
	
	@Mock
	AppointmentRepo appointmentRepoMock;
	
	private static Appointment appointment;
	private static Appointment appointment1;
	private static Appointment appointment2;
	private static Appointment appointment3;
	private static Appointment appointmentSecond;
	private static List<Appointment> appointmentList = new ArrayList<>();
	
	
	@BeforeEach
	public void init() {

		appointment = new Appointment("121", BigInteger.valueOf(15), "23", LocalDateTime.of(2020,8 , 27	, 9, 30), false);
		appointmentSecond = new Appointment("120", BigInteger.valueOf(15), "22", LocalDateTime.of(2020,8 , 28, 9, 30), false);
		appointment1=new Appointment("230", BigInteger.valueOf(25), "13", LocalDateTime.of(2020, 8, 25, 5, 30), false);
		appointment2=new Appointment("231", BigInteger.valueOf(0) , "14", LocalDateTime.of(2020,9 , 15, 11, 30), false);
		appointment3= new Appointment("232", BigInteger.valueOf(5) , "15", LocalDateTime.of(2020,8 , 15, 11, 30), false);
	
	}
	
	@Test
	public void getAppointmentTest() {
		Mockito.when(appointmentRepoMock.getOne(BigInteger.valueOf(15))).thenReturn(appointment);
		Appointment app = appointmentServiceMock.getAppointment(appointment.getAppointmentId());
		BigInteger id = app.getAppointmentId();
		assertEquals(15, id.intValue());
	}
	
	@Test
	public void getAppointmentStatusTest() {
		Mockito.when(appointmentRepoMock.getOne(BigInteger.valueOf(15))).thenReturn(appointment);
		
		assertFalse(appointmentServiceMock.getAppointment(appointment.getAppointmentId()).isApproved());
	}
	
	@Test()
	public void slotNotAvailableTest() {
		//Mockito.when(appointmentRepoMock.save(appointment1)).thenThrow(new SlotNotAvailableException("Error:Enter time between 08:00 to 17:00"));
		//Mockito.when(appointmentRepoMock.save(appointment2)).thenThrow(new SlotNotAvailableException("Error:Cannot make appointment, choose a date closer to current date"));
		//Mockito.when(appointmentRepoMock.save(appointment3)).thenThrow(new SlotNotAvailableException("Error:Cannot make appointment, choose a date closer to current date"));

		assertThrows(SlotNotAvailableException.class, () -> {appointmentServiceMock.makeAppointment(appointment1);});
		assertThrows(SlotNotAvailableException.class, () -> {appointmentServiceMock.makeAppointment(appointment2);});
		assertThrows(SlotNotAvailableException.class, () -> {appointmentServiceMock.makeAppointment(appointment3);});
	}
	
	
	@Test
	public void makeAppointmentTest() {
		//Mockito.when(appointmentRepoMock.save(appointment)).thenReturn(appointment);
		assertEquals(appointment, appointmentServiceMock.makeAppointment(appointment));
	}
	
	@Test
	public void removeAppointmentTest() {
		Mockito.when(appointmentRepoMock.save(appointment)).thenReturn(null);
	
		assertEquals(true, appointmentServiceMock.removeAppointmentById(appointment.getAppointmentId()));
	}
	
	@Test
	public void getAllAppointments() {
		
		appointmentList.add(appointment);
		appointmentList.add(appointmentSecond);
		Mockito.when(appointmentRepoMock.findAll()).thenReturn(appointmentList);
			
		assertEquals(2, appointmentServiceMock.getAllAppointments().size());
	}
		
}



















