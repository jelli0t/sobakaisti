package org.sobakaisti.mvt.dao.impl;

import java.util.List;
import java.util.Map;

import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post.Origin;


public interface CommentDao {

	List<Comment> getPostComments(int postId, Origin postOrigin, int from, int max);

	Comment save(Comment comment);

	/**
	 * Vraca broj aktivnih komentara na postu
	 */
	public int countCommentsPerPost(int postId, Origin postOrigin);
	
	/**
	 * 
	 * */
	public List<Comment> getCommentsBundle(int postId, Origin postOrigin, int from, int size);

	/**
	 * Generise mapu PostID => Br.kometara na tom postu. <br>
	 * Mapa vazi samo za komentare prosledjenog porekla.
	 * */
	public Map<Integer, Integer> generatePostIdToCommentsCountMap(Origin postOrigin);
}
