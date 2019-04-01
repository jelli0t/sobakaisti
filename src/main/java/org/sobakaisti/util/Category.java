package org.sobakaisti.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Category {

	LITERATURE,
	MUSIC,
	PHOTOGRAPHY,
	VIDEO,
	PAINTINGS;
	
	public String toLower() {
		return this.name().toLowerCase();
	}
	
	public static Category getCategory(String name) {
		if(TextUtil.notEmpty(name)) {
			name = name.trim().toUpperCase();
			return Category.valueOf(name);
		}
		return null;
	}
	
	public List<Category> sortWithChosenOnTop(String name) {
		return (List<Category>) Arrays.asList(this.values())
				.stream().sorted(Comparator.comparingInt(p -> isEquals(name, p)));		
		
	}
	
	private int isEquals(String name, Category category) {
		return category.equals(Category.getCategory(name)) ? 1 : 0;
	}
}
