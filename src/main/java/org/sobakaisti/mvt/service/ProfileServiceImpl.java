package org.sobakaisti.mvt.service;

@Service
public abstract class ProfileServiceImpl<T extends Profile> implements ProfileService<T> {

  private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);
  
  @Autowired
  private ProfileDao<T> postDao;
  
  @Override
  boolean removeSocialNetwork(int snid) {
    return postDao.removeSocialNetwork(int snid);
  }
  
}
