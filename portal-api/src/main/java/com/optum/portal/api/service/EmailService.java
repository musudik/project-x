package com.optum.portal.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	/**
	 *
	 * @param emailSender
	 */
	public EmailService(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}

	/**
	 *
	 * @param to
	 * @param subject
	 * @param text
	 */
	public void sendSimpleMessage(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();/// message.setFrom(NOREPLY_ADDRESS);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);

			emailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
		}
	}
}