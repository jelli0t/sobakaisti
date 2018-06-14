package org.sobakaisti.mvt.service;
/**
 * @author jelli0t
 *
 */
public interface MailService {	
	/**
	 * Salje MailMessage
	 * */
	public boolean send(MailMessage message);

}
