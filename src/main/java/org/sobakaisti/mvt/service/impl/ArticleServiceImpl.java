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
 * @author jelles
 *
 */
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	
	private int charsPerRow;
	private int rowsPerPage;
	private int charsToFill;
	
	@Override
	public List<String> getRowsFromArticleWithDimension(int width, int height, double charWidth) {
		charsPerRow = (int) Math.floor(width / charWidth);
		rowsPerPage = (int) Math.floor(height / LINE_HEIGHT);
		String content = articleDao.getArticleById(1);
		int length = content.length();
		charsToFill = (int) (charsPerRow - Math.ceil(length % charsPerRow));
		
		System.out.println("Params: Dimensions: "+width+"x"+height+"; Char width: "+charWidth+"; karaktera za popunjavanje: "+charsToFill
				+ " Chars x Rows: "+charsPerRow+"x"+rowsPerPage);
		
		List<String> row = new ArrayList<>(rowsPerPage);
		
		if(!content.equals("")){
			for(int i=0, j=0; i<rowsPerPage; i++){				
				if(j<length-charsPerRow){					
					row.add(content.substring(j, j+charsPerRow));
//					System.out.println(i+". j="+j);
					j+=charsPerRow;
				}else if(j < length){
					String ending = content.substring(j)+" "+content.substring(0, charsToFill-1);
					row.add(ending);
					j = 0;
				}
			}
		}
		return row;
	}
	
	
}
