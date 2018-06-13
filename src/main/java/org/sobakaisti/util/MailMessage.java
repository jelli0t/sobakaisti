package org.sobakaisti.util;

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
	private String fromMail;
	/**
	 * Naslov E-mail poruke
	 */
	private String subject;
	/**
	 * Sadrzaj E-mail poruke
	 */
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

}
