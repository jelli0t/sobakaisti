/**
 * 
 */
package org.sobakaisti.util;

/**
 * @author nemanja
 *
 */
public enum Visibility {
	PUBLIC(1),
	PRIVATE(0),
	DRAFT(-1);
	
	private int value;

	private Visibility(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}
}
