package org.sobakaisti.mvt.security.model;

/**
* Reprezentuje user account entitet sa kojim se 
* korisnik loguje na sistem.
*/
public class User implements UserDetails {
  
  private String username;
  private String password;
  private Authority.Role role;

}
