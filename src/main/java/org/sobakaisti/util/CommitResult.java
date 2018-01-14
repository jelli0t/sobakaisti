/**
 * 
 */
package org.sobakaisti.util;

/**
 * @author Korisnik
 * Pomocni objekat za ispis rezultata neke operacije.
 * Koristan za Ajax operacije
 */
public class CommitResult {
	
	private Boolean commited;
	private String commitMessage;

	public CommitResult() {
		super();
	}
	
	public CommitResult(boolean commited, String commitMessage) {
		super();
		this.commited = new Boolean(commited);
		this.commitMessage = commitMessage;
	}
	public Boolean getCommited() {
		return commited;
	}
	public void setCommited(Boolean commited) {
		this.commited = commited;
	}
	public String getCommitMessage() {
		return commitMessage;
	}
	public void setCommitMessage(String commitMessage) {
		this.commitMessage = commitMessage;
	}
	
	public boolean isCommited() {
		return (this.commited != null && this.commited.booleanValue());
	}
}
