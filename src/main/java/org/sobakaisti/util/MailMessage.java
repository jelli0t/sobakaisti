package org.sobakaisti.util;

/**
 * @author jelli0t
 * Reprezentuje pomocni objekta za prihvatanje poruke sa kontakt forme 
 * i slanje tih podataka na e-mail.
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
}
