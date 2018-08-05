package org.sobakaisti.mvt.service;

import java.util.List;
import java.util.Map;

import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Post.Origin;

public interface CommentService {

	public static final String COMMENTS_COUNT_MODEL_ATTRIBUTE_NAME = "COMMENTS_COUNT";
	public static final String COMMENTS_AVAILABLE_ATTR_NAME = "COMMENTS_AVAILABLE";
	public static final String COMMENTS_COUNT_PER_POST_ATTR_NAME = "COMMENTS_COUNT_PER_POST";
	public static final int COMMENTS_BUNDLE_LOAD_DEFAULT_SIZE = 10;

	public Comment populateAndSave(Comment comment, Class<? extends Post> postType);

	List<Comment> findPostComments(int postId, Origin postOrigin, int from, int max);

	/**
	 * Count comments per post
	 */
	int countPostComments(int postId, Origin postOrigin);

	/**
	 * Za navedeni post 
	 * */
	public List<Comment> commentsBundleLoad(int postId, Origin postOrigin, int loaded, int size);

	/**
	 * Komentar sa forme autorizuje. <br>
	 * Proverava da li je autor registrovan korisnik il anonymous. <br>
	 * pa cuva komntar
	 * */
	Comment authorizeAndSave(Comment comment);

	/**
	 * Dohvata mapu post_id => comments_count, za postove porsledjenog porekla.
	 * */
	Map<Integer, Integer> getPostToCommentsCountMap(Origin postOrigin);
}
