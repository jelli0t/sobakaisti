package org.sobakaisti.util;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author jelli0t Reprezentuje pomocni objekta za prihvatanje poruke sa kontakt
 *         forme i slanje tih podataka na e-mail.
 * 
 */
public class MailMessage {
	/**
	 * E-mail adresa primaoca poruke
	 */
	private String to;
	/**
	 * Ime posiljaoca
	 */
	private String fromName;
	/**
	 * E-mail adresa posiljaoca poruke
	 */
	@NotEmpty(message="{validation.warn.mail.notEmpty}")
	@Pattern(regexp="^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$",
		message="{validation.warn.mailPattern}")
	private String fromMail;
	/**
	 * Naslov E-mail poruke
	 */
	@NotEmpty(message="{validation.warn.suject.notEmpty}")
	@Size(min=2, max=250, message="{validation.warn.title.size}")
	private String subject;
	/**
	 * Sadrzaj E-mail poruke
	 */
	@NotEmpty(message="{validation.warn.mail.body.notEmpty}")	
	private String message;
	/**
	 * flag za tip tela poruke. HTML vs. Plane text
	 */
	private boolean html;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isHtml() {
		return html;
	}

	public void setHtml(boolean html) {
		this.html = html;
	}

	public void prefixMailSubject(String prefix) {
		if(TextUtil.notEmpty(prefix) & TextUtil.notEmpty(this.subject)) {
			setSubject(prefix + TextUtil.SPACE_CHAR + this.subject);
		}
	}
}
