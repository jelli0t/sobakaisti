package org.sobakaisti.mvt.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.models.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public abstract class AbstractProfileDao<T extends Profile> implements ProfileDao<T> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractProfileDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
    	
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T findProfile(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeSocialNetwork(int snid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeProfilesSocialNetwork(int snid, int profileId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAllProfilesSocialNetworks(int profile) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public T saveOrUpdateProfile(T t) {
		try {
			currentSession().saveOrUpdate(t);
			return t;
		} catch (Exception e) {
			logger.warn("Greska pri cuvanju enititeta. Uzrok: "+e.getMessage());
			return null;
		}
	 }

}
