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
	CONTACT_FORM_MAIL("contactFormMessage", false);
	
	private String name;
	private boolean html;

	private MailTemplate(String name, boolean html) {
		this.name = name;
		this.html = html;
	}

	public String getName() {
		return name;
	}

	public boolean isHtml() {
		return html;
	}
	
	
	
}
