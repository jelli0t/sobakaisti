package org.sobakaisti.mvt.service;

import java.util.List;

public interface ArticleService {

	public static final int LINE_HEIGHT = 16; 		// (px) sirina jednog slova
	public static final double FONT_WIDTH = 7.25;		// (px) visina jednog reda definisano CSS-om 
	public List<String> getRowsFromArticleWithDimension(int width, int height);
}