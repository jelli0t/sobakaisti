package org.sobakaisti.mvt.service;


@Service
public class MailServiceImpl implements MailService {
  private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

  @Autowired
  private JavaMailSender mailSender;

  @Override
  public boolean send(MailMessage message) {
    boolean sent = false;
    if(message != null) {
      final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
      final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
      message.setFrom(message.getFromMail());
      message.setTo(message.getTo());
      message.setSubject(message.getSubject());
      message.setText(message.getMessage());
      
      this.mailSender.send(mimeMessage);
    }
    return sent;
  }
}
