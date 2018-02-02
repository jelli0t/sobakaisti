/**
 * 
 */
package org.sobakaisti.mvt.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.transform.ResultTransformer;
import org.sobakaisti.mvt.i18n.model.I18nPost;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Author;
import org.sobakaisti.mvt.models.Media;
import org.sobakaisti.mvt.models.Post;

/**
 * @author jelli0t
 *
 */
public class I18nPostResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = -6953387781583945455L;	

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Article article = null;
		try {
			article = new Article((int) tuple[0], (String) tuple[1], (String) tuple[2], (Calendar) tuple[3], (String) tuple[4], 
					(int) tuple[5], (Author) tuple[6], (String) tuple[7], (Media) tuple[8]);
		} catch (ClassCastException cce) {
			System.err.println("Cast exception! "+cce.getMessage());
		} catch (Exception e) {
			System.err.println("Error! "+e.getMessage());
		} 
		
		System.out.println("ResultTranformed: "+article);
		
		return article;
	}


	@Override
	public List transformList(List collection) {
		return collection;
	}

}
