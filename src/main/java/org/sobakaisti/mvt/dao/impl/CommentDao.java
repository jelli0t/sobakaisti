package org.sobakaisti.mvt.dao.impl;

import java.util.List;
import java.util.Map;

import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.enums.PostOrigin;

public interface CommentDao {

	List<Comment> getPostComments(int postId, PostOrigin postOrigin, int from, int max);

	Comment save(Comment comment);

	int countCommentsPerPost(int postId, PostOrigin postOrigin);
	
	List<Comment> getCommentsBundle(int postId, PostOrigin postOrigin, int from, int size);

	/**
	 * Generise mapu PostID => Br.kometara na tom postu. <br>
	 * Mapa vazi samo za komentare prosledjenog porekla.
	 * */
	Map<Integer, Integer> generatePostIdToCommentsCountMap(PostOrigin postOrigin);
}
