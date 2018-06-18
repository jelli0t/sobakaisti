package org.sobakaisti.mail;


public class MailTemplateHelper {

  protected static final String CONTACT_MESSAGE_VAL_NAME = "contactMessage";
  public static final String CONTACT_FORM_MAIL_TEMP_NAME = "contactFormMessage";
  
  protected static final Map<String, MailMessage> CONTACT_MESSAGE_VAL_MAP = new HashMap<>(1) {
    {
      put(CONTACT_MESSAGE_VAL_NAME, new MailMessage());
    }
  };
  
  protected static final List<String> CONTACT_MESSAGE_VAL_LIST = new ArrayList<>(Arrays.asList(new String[] {CONTACT_MESSAGE_VAL_NAME}));

 
  public enum MailTemplate {
    /**
     * Template poruke sa kontakt forme
     * */
    CONTACT_FORM_MAIL(CONTACT_FORM_MAIL_TEMP_NAME, false, CONTACT_MESSAGE_VAL_LIST);

    private String name;
    private boolean html;
    private List<String> messageValNames;
 //   private Map<String, Object> messageValMap;

    private MailTemplate(String name, boolean html, List<String> messageValNames) {
      this.name = name;
      this.html = html;
      this.messageValNames = messageValNames;
    }

    public String getName() {
      return name;
    }

    public boolean isHtml() {
      return html;
    }    

    public String getMessageValNames() {
      return messageValNames;
    }
    
     public static MailTemplate findTemplateByName(String name){
	        for(MailTemplate templ : MailTemplate.values()){
	            if( templ.getName().equals(name))
	                return templ;
	        }
	        return null;
	    }
  }
  
  public void delegate
  
}
