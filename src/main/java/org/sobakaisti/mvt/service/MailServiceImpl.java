package org.sobakaisti.mvt.service;


@Service
public class MailServiceImpl implements MailService {
  private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

  @Autowired
  private JavaMailSender mailSender;

  @Override
  public boolean send(MailMessage message) {
    return null;
  }
}
