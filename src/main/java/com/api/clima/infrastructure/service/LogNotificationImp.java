package com.api.clima.infrastructure.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.clima.domain.ILogNotification;
import com.api.clima.entrypoints.dto.LogNotificationDto;
import com.api.clima.infrastructure.persistence.ILogNotificationRepository;
import com.api.clima.infrastructure.persistence.LogNotificationEntity;

import jakarta.transaction.Transactional;

@Service
public class LogNotificationImp implements ILogNotification{
	
	private final ILogNotificationRepository repository;
	
	LogNotificationImp(ILogNotificationRepository repository){
		this.repository = repository;
	}
	
	public LogNotificationEntity covertEntity(LogNotificationDto log) {
		LogNotificationEntity logEntity = new LogNotificationEntity();
		logEntity.setCorreo(log.getCorreo());
		logEntity.setFecha(log.getFecha());
		logEntity.setUbicacion(log.getUbicacion());
		logEntity.setDesClima(log.getDesClima());
		
		return logEntity;
	}
	
	public LogNotificationDto covertDto(LogNotificationEntity log) {
		LogNotificationDto logDto = new LogNotificationDto();
		logDto.setCorreo(log.getCorreo());
		logDto.setFecha(log.getFecha());
		logDto.setUbicacion(log.getUbicacion());
		logDto.setDesClima(log.getDesClima());
		
		return logDto;
	}
	
	public List<LogNotificationDto> convertDtoList(List<LogNotificationEntity> logList) {
	    return logList.stream()
	                      .map(log -> new LogNotificationDto(
	                          log.getCorreo(),
	                          log.getFecha(),
	                          log.getUbicacion(),
	                          log.getDesClima()))
	                      .collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public void saveLogNotification(LogNotificationDto LogNotification) {
		LogNotificationEntity log = covertEntity(LogNotification);
		repository.save(log);
	}
	
	@Override
	public List<LogNotificationDto> findByCorreo(String email){
		List<LogNotificationEntity> response = repository.findByCorreo(email);
		
		List<LogNotificationDto> log = convertDtoList(response);
		
		return log.stream()
                .map(entity -> new LogNotificationDto(
                        entity.getCorreo(),
                        entity.getFecha(),
                        entity.getUbicacion(),
                        entity.getDesClima()
                ))
                .collect(Collectors.toList());
	}

}
