package org.sobakaisti.mail;


public class MailTemplateHelper {

  protected static final String CONTACT_MESSAGE_VAL_NAME = "contactMessage";
  
  protected static final Map<String, MailMessage> CONTACT_MESSAGE_VAL_MAP = new HashMap<>(1) {
    {
      put(CONTACT_MESSAGE_VAL_NAME, new MailMessage());
    }
  };

 

  

}
