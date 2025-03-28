package com.api.clima.infrastructure.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSendException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {
	
	@Mock private JavaMailSender mailSender;
	
	@InjectMocks EmailService emailService;

	private String email;
	private String text;
	
	@BeforeEach
	void setup() {
		email = "LexyBrigitte@gmail.com";
		text = "Nublado";
	}
	
	@Test
	void sendEmailSuccess() {
		doNothing().when(mailSender).send(any(SimpleMailMessage.class));
		
		assertDoesNotThrow(() -> emailService.sendEmail(email, text));

		verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
	}
	
	@Test
	void testSendEmailFail() {
		
		doThrow(new MailSendException("Error enviando correo")).when(mailSender).send(any(SimpleMailMessage.class));
				
		assertThrows(MailSendException.class, () -> emailService.sendEmail(email, text));

		verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
	}
}
