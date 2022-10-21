package com.javaproject.service;

public interface IEmailService {
	// To send a simple email
	void sendSimpleMail(EmailDetails details);

	// To send an email with attachment
	String sendMailWithAttachment(EmailDetails details);
}
