package org.sobakaisti.mvt.service;

@Service
public abstract class ProfileServiceImpl<T extends Profile> {

  @Autowired
  private ProfileDao<T> postDao;
  
  
  
}
