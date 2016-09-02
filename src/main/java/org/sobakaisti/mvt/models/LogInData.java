/**
 * 
 */
package org.sobakaisti.mvt.models;

/**
 * @author jelles
 *	Simpla POJO to carry on user login data.
 */
public class LogInData {
	private String principal;
	private String credential;
	private boolean remembered;
	
	public String getPrincipal() {
		return principal;
	}
	public String getCredential() {
		return credential;
	}
	public boolean isRemembered() {
		return remembered;
	}	
}