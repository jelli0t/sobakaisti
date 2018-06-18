/**
 * 
 */
package org.sobakaisti.mail;

import java.util.Arrays;
import java.util.List;

/**
 * @author Korisnik
 *
 */
public enum MailTemplate {
	/**
	 * Template poruke sa kontakt forme
	 * */
	CONTACT_FORM_MAIL("contactFormMessage", false, CONTACT_MESSAGE_VAL_MAP);
	
	private String name;
	private boolean html;
	private Map<String, Object> messageValMap;

	private MailTemplate(String name, boolean html, Map<String, Object> messageValMap) {
		this.name = name;
		this.html = html;
		this.messageValMap = messageValMap;
	}

	public String getName() {
		return name;
	}

	public boolean isHtml() {
		return html;
	}
		
}
