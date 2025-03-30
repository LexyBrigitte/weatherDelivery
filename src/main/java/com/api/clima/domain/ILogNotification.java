package com.api.clima.domain;

import java.util.List;

import com.api.clima.entrypoints.dto.LogNotificationDto;

public interface ILogNotification {

	void saveLogNotification(LogNotificationDto logNotificationDto);
	List<LogNotificationDto> findByCorreo(String email);
}
