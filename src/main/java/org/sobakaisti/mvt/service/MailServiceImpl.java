package org.sobakaisti.mvt.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.util.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;

	@Override
	public boolean send(MailMessage message) {
		boolean sent = false;
		if (message != null) {
			final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
			final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
			try {
				messageHelper.setFrom(message.getFromMail());
				messageHelper.setTo(message.getTo());
				messageHelper.setSubject(message.getSubject());
				messageHelper.setText(message.getMessage());
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.mailSender.send(mimeMessage);
		}
		return sent;
	}
}
