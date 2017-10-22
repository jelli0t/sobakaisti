package org.sobakaisti.util;

import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.mvt.service.PostService;
import org.sobakaisti.mvt.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jelli0t
 * Abstract factory Pattern implementacija 
 * za kreiranje konkretnih Post podklasa
 */
public abstract class PostFactory {
	
	private static final ArticlePostFactory ARTICLE_FACTORY = new ArticlePostFactory();
    private static final PublicationPostFactory PUBLICATION_FACTORY = new PublicationPostFactory();
    
    @Autowired
    protected TagService tagService;
    
    /**
     * Kreira konkretnu implementaciju PostFactory u zavisnosti od tipa
     * prosledjenog objekta. 
     * @param clazz
     * */
    public static PostFactory getFactory(Class<? extends Post> clazz) {
    	if(clazz.isInstance(Article.class)) {
    		return ARTICLE_FACTORY;
    	} 
    	else if(clazz.isInstance(Publication.class)) {
    		return PUBLICATION_FACTORY;
    	}
    	return null;
    }
    
    /**
     * Obradjuje PostRequest objekat iz requesta sa forme za novi Post
     * @param postRequest
     * */
    abstract public Post processPostRequest(PostRequest postRequest);

	
    
}
