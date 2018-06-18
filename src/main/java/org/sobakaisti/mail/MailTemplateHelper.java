package org.sobakaisti.mail;

import java.util.HashMap;
import java.util.Map;

import org.sobakaisti.util.MailMessage;

public class MailTemplateHelper {

  protected static final String CONTACT_MESSAGE_VAL_NAME = "contactMessage";
  public static final String CONTACT_FORM_MAIL_TEMP_NAME = "contactFormMessage";
  
  protected static final Map<String, MailMessage> CONTACT_MESSAGE_VAL_MAP = new HashMap<String, MailMessage>(1) {
    {
      put(CONTACT_MESSAGE_VAL_NAME, null);
    }
  };
  
 
  public enum MailTemplate {
    /**
     * Template poruke sa kontakt forme
     * */
    CONTACT_FORM_MAIL(CONTACT_FORM_MAIL_TEMP_NAME, false, null);

    private String name;
    private boolean html;
 //   private List<String> messageValNames;
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

    public Map<String, Object> getMessageValMap() {
      return messageValMap;
    }
    
     public static MailTemplate findTemplateByName(String name){
	for(MailTemplate templ : MailTemplate.values()){
	    if( templ.getName().equals(name))
		return templ;
	}
	return null;
    }
  }
  
  
}
