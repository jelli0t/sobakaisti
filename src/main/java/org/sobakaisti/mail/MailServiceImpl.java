package org.sobakaisti.mail;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.util.MailMessage;
import org.sobakaisti.util.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailServiceImpl implements MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private JavaMailSender mailSender;
		
	@Autowired
	private TemplateEngine mailTemplateEngine;
    
//    @Autowired
//    private TemplateEngine htmlTemplateEngine;
    
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
				// Send!
				this.mailSender.send(mimeMessage);
				sent = true;
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return sent;
	}
	
	@Override
	public boolean sendPlaneTextMail(MailMessage mailMessage) {
		boolean sent = false;
		// Prepare the evaluation context
		final Context ctx = new Context();
		ctx.setVariable("fromName", mailMessage.getFromName());
		ctx.setVariable("fromMail", mailMessage.getFromMail());
		ctx.setVariable("subject", mailMessage.getSubject());
		ctx.setVariable("message", mailMessage.getMessage());
		ctx.setVariable("sentOnDate", new Date());
		
		MailTemplate mailTemplate = MailTemplate.findTemplateByName(mailMessage.getMailTemplateName());
		for(String varName : mailTemplate.getMessageValNames())
			ctx.setVariable(varName, mailMessage);
		
		final String messageContent = this.mailTemplateEngine.process(mailTemplate.getName(), ctx);
		mailMessage.setMessage(messageContent);
		System.out.println("mail_body: "+messageContent);

		// Prepare message using a Spring helper
	/*	final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, TextUtil.UTF8_CHAR_ENCODING);

		try {
				message.setTo(DEFAULT_MAILTO_ADDRESS);		
			message.setFrom(mailMessage.getFromMail());
			message.setSubject(mailMessage.getSubject());	
			// Create the plain TEXT body using Thymeleaf
			//final String textContent = this.mailTemplateEngine.process(MailTemplate.CONTACT_FORM_MAIL.getName(), ctx);
			message.setText(textContent);
			
			this.mailSender.send(mimeMessage);
			sent = true;
	
		
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/	
        	return this.send(mailMessage);
	}
}
