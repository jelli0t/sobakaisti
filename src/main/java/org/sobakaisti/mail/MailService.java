package org.sobakaisti.mail;

import org.sobakaisti.util.MailMessage;

/**
 * @author jelli0t
 *
 */
public interface MailService {	
	
	public static final String DEFAULT_MAILTO_ADDRESS = "info@sobakaisti.org";
	/**
	 * Salje MailMessage
	 * */
	public boolean send(MailMessage message);
	
	/**
	 * 
	 * */
	boolean sendPlaneTextMail(MailMessage mailMessage);

}
