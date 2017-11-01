package org.sobakaisti.mvt.service;

import org.sobakaisti.mvt.models.Article;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Publication;
import org.sobakaisti.util.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jelli0t
 * Abstract factory Pattern implementacija 
 * za kreiranje konkretnih Post podklasa
 */
public abstract class PostFactory {
	
//	private static final ArticlePostFactory ARTICLE_FACTORY = new ArticlePostFactory();
//    private static final PublicationPostFactory PUBLICATION_FACTORY = new PublicationPostFactory();
    
    @Autowired
    protected ArticlePostFactory articlePostFactory;
    
    @Autowired
    protected PublicationPostFactory publicationPostFactory;
    
    @Autowired
    protected TagService tagService;
    
    /**
     * Kreira konkretnu implementaciju PostFactory u zavisnosti od tipa
     * prosledjenog objekta. 
     * @param clazz
     * */
    public PostFactory getFactory(Class<? extends Post> clazz) {
    	System.out.println("Clazz: "+clazz.getSimpleName());

    	if(Publication.class.isAssignableFrom(clazz)) {
    		System.out.println("Pravim instacu PublicationPostFactory.class isAssgnableFrom");
    		return publicationPostFactory;
    	}
    	
    	if(clazz.isInstance(Article.class)) {
    		return articlePostFactory;
    	} 
    	else if(clazz.isInstance(Publication.class)) {
    		System.out.println("Pravim instacu PublicationPostFactory.class");
    		return publicationPostFactory;
    	}
    	
    	System.out.println("Nista ne instanciram vracam null!");
    	return null;
    }
    
    /**
     * Obradjuje PostRequest objekat iz requesta sa forme za novi Post
     * @param postRequest
     * */
    abstract public Post processPostRequest(PostRequest postRequest);    
}
