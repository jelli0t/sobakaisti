package org.sobakaisti.mvt.dao;

/**
 * @author jelles
 *
 */
public interface ProfileDao<T extends Profile> {
  
  /**
  * Pronalazi profil na osnovu prosledjenog ID-a
  **/
  T findProfile(int id);
  
  /**
  * Uklanja SocilaNetwork na samo na osnovu ID mreze.
  **/
  boolean removeSocialNetwork(int snid);
  
  /**
  * Uklanja SocilaNetwork na na osnovu ID mreze za profil sa ID.
  **/
  boolean removeProfilesSocialNetwork(int snid, int profileId);
  
  /**
  * Uklanja sve Drustvene mreze za profil sa ID.
  **/
  boolean removeAllProfilesSocialNetworks(int profile);
  
}
