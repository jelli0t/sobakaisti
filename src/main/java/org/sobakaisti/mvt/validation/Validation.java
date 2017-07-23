package org.sobakaisti.mvt.validation;

public class Validation {

	private boolean errors;
	private String fieldName;
	private String errorMessage;
	
	/* default contructor */
	public Validation() {}
	public Validation(boolean hasErrors) {
		this.errors = hasErrors;
	}
	
	public boolean hasErrors() {
		return errors;
	}

	public void setHasErrors(boolean errors) {
		this.errors = errors;
	}

	public boolean isErrors() {
		return errors;
	}

	public void setErrors(boolean errors) {
		this.errors = errors;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
