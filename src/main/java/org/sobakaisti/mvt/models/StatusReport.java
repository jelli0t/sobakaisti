/**
 * 
 */
package org.sobakaisti.mvt.models;

/**
 * @author jelles
 * POJO which represent status of some validation and used as a JSON response
 */
public class StatusReport {
	private String status;
	private String massage;
	
	public StatusReport(String status){
		this.status = status;
	}
	public StatusReport(String status, String massage){
		this.status = status;
		this.massage = massage;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}	
}