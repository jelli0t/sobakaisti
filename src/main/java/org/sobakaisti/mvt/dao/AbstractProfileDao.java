package org.sobakaisti.mvt.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sobakaisti.mvt.models.Profile;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractProfileDao<T extends Profile> implements ProfileDao<T> {
	private static final Logger logger = LoggerFactory.getLogger(AbstractProfileDao.class);

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

}
