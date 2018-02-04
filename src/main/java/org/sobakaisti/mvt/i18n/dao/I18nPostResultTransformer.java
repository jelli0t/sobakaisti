/**
 * 
 */
package org.sobakaisti.mvt.i18n.dao;

import java.util.List;

import org.hibernate.transform.ResultTransformer;
import org.sobakaisti.mvt.i18n.model.I18nArticle;
import org.sobakaisti.mvt.i18n.model.I18nPublication;
import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;

/**
 * @author jelli0t
 *
 */
public class I18nPostResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = -6953387781583945455L;	

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Post post = null;
		try {
			if(tuple[0] != null && tuple[1] != null) {
				if(tuple[0] instanceof Article && tuple[1] instanceof I18nArticle) {
					post = new Article((Article) tuple[0], (I18nArticle) tuple[1]); 
				}
				else if (tuple[0] instanceof Publication && tuple[1] instanceof I18nPublication) {
					post = new Publication((Publication) tuple[0], (I18nPublication) tuple[1]);
				}
			}
			else {
				post = null;
			}			
		} catch (ClassCastException cce) {
			System.err.println("Cast exception! "+cce.getMessage());
		} catch (Exception e) {
			System.err.println("Error! "+e.getMessage());
		}		
		return post;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return collection;
	}

}