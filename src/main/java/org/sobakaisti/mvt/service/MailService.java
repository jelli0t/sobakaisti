package org.sobakaisti.mvt.service;

import org.sobakaisti.util.MailMessage;

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
