package org.sobakaisti.mvt.service;

/**
 * @author jelli0t
 *
 */
public interface ProfileService<T extends Profile> {

  /**
  * Uklanja podatke o profilu na soc.mrezi, samo na osnovu ID-a.
  **/
  boolean removeSocialNetwork(int snid);
}
