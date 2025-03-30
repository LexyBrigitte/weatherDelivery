package com.api.clima.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.clima.domain.ILogNotification;
import com.api.clima.entrypoints.dto.LogNotificationDto;

@ExtendWith(MockitoExtension.class)
public class LogNotificationCaseTest {

	@Mock private ILogNotification logNotification;
	
	@InjectMocks private LogNotificationCase logNotificationCase;
	
	private String email;
	
	@BeforeEach
	void setUp() {
		email = "lexybrigitte@gmail.com";
	}
	
	public void getLogNotificationTestSucces() {
		
		List<LogNotificationDto> responseLog = new ArrayList<>();
		
		responseLog.add(new LogNotificationDto("lexybrigitte@gmail.com",LocalDateTime.now(),"Tachira","Lluvia moderada"));
		
		when(logNotification.findByCorreo(email)).thenReturn(responseLog);
		
		List<LogNotificationDto> resul = logNotificationCase.getLogNotificationCase(email);
		
		assertEquals(responseLog.get(0).getCorreo(), resul.get(0).getCorreo());
		assertEquals(responseLog.get(0).getFecha(), resul.get(0).getFecha());
		assertEquals(responseLog.get(0).getUbicacion(), resul.get(0).getUbicacion());
		assertEquals(responseLog.get(0).getDesClima(), resul.get(0).getDesClima());
	}
}
