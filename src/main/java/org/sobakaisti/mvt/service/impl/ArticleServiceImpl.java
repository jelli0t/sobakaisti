/**
 * 
 */
package org.sobakaisti.mvt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.sobakaisti.mvt.dao.ArticleDao;
import org.sobakaisti.mvt.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author NEKS Office
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	private int charsPerRow;
	private int rowsPerPage;
	
	@Override
	public List<String> getRowsFromArticleWithDimension(int width, int height) {
		charsPerRow = (int) Math.floor(width / FONT_WIDTH);
		rowsPerPage = (int) Math.floor(height / LINE_HEIGHT);
		System.out.println("Params: Dimensions: "+width+"x"+height+";"
				+ " Chars x Rows: "+charsPerRow+"x"+rowsPerPage);
		List<String> row = new ArrayList<>(rowsPerPage);
		String content = articleDao.getArticleById(1);
		int length = content.length();
		if(!content.equals("")){
			for(int i=0, j=0; i<=rowsPerPage; i++, j+=charsPerRow){				
				if(j<length-charsPerRow){
					System.out.println(i+". j="+j);
					row.add(content.substring(j, j+charsPerRow));				
				}else if(j < length){
					row.add(content.substring(j));
				}else if(row.size() <= rowsPerPage){
					j = 0;
				}
			}
		}
		return row;
	}
	
	
}
