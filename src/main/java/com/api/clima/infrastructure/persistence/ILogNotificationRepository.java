package com.api.clima.infrastructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.clima.entrypoints.dto.LogNotificationDto;

@Repository
public interface ILogNotificationRepository extends JpaRepository<LogNotificationEntity, Integer>{
	
	List<LogNotificationEntity> findByCorreo(String email);
}
