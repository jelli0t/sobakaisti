package org.sobakaisti.mvt.service;

import org.sobakaisti.mvt.models.Profile;

/**
 * @author jelli0t
 *
 */
public interface ProfileService<T extends Profile> {

  /**
  * Uklanja podatke o profilu na soc.mrezi, samo na osnovu ID-a.
  **/
  boolean removeSocialNetwork(int snid);
  
  public T saveOrUpdateProfile(T t);
}
