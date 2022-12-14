package org.sobakaisti.mail;

import org.sobakaisti.util.MailMessage;

public interface MailService {
	
	public static final String DEFAULT_MAILTO_ADDRESS = "info@sobakaisti.org";

	public boolean send(MailMessage message);
	
	boolean sendPlaneTextMail(MailMessage mailMessage);

}
