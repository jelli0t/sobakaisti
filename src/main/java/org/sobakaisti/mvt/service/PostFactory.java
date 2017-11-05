package org.sobakaisti.mvt.service;

import org.sobakaisti.mvt.dao.AuthorDao;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jelli0t
 * Abstract factory Pattern implementacija 
 * za kreiranje konkretnih Post podklasa
 */
public abstract class PostFactory {
    
    @Autowired
    protected TagService tagService;
    
    @Autowired
    protected AuthorDao authorDao;
    
    /**
     * Kreira konkretnu implementaciju PostFactory u zavisnosti od tipa
     * prosledjenog objekta. 
     * @param clazz
     * */
    public PostFactory getFactory(Class<? extends Post> clazz) {
//    	System.out.println("Clazz: "+clazz.getSimpleName());
//
//    	if(Publication.class.isAssignableFrom(clazz)) {
//    		System.out.println("Pravim instacu PublicationPostFactory.class isAssgnableFrom");
//    		return publicationPostFactory;
//    	}
//    	
//    	if(clazz.isInstance(Article.class)) {
//    		return articlePostFactory;
//    	} 
//    	else if(clazz.isInstance(Publication.class)) {
//    		System.out.println("Pravim instacu PublicationPostFactory.class");
//    		return publicationPostFactory;
//    	}
    	return null;
    }
    
    /**
     * Obradjuje PostRequest objekat iz requesta sa forme za novi Post
     * @param postRequest
     * */
    abstract public Post processPostRequest(PostRequest postRequest);    
}
