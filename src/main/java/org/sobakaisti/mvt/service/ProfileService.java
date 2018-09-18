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
  /**
  * Izvrsi brisanje drustvene mreze sa ID, za profil sa prosledjenim ID.<br>
  * Vraca CommitResult kao odgovora na akciju.
  **/
  public CommitResult commitProfilSocialNetworkDeletion(int profileId, int socnetId);
}
