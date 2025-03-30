package com.api.clima.application;

import java.util.List;

import org.springframework.stereotype.Service;
import com.api.clima.domain.ILogNotification;

import com.api.clima.entrypoints.dto.LogNotificationDto;

@Service
public class LogNotificationCase {

	private final ILogNotification logNotification;
	
	public LogNotificationCase(ILogNotification logNotification) {
		this.logNotification = logNotification;
	}
	
	public List<LogNotificationDto> getLogNotificationCase (String email){
		
		return logNotification.findByCorreo(email);
	}
}
