package org.sobakaisti.mvt.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sobakaisti.mvt.dao.impl.CommentDao;
import org.sobakaisti.mvt.models.Comment;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.enums.PostOrigin;
import org.sobakaisti.mvt.service.CommentService;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentDao commentDao;
	
	@Override
	public List<Comment> findPostComments(int postId, PostOrigin postOrigin, int from, int max) {
		return commentDao.getPostComments(postId, postOrigin, from, max);
	}

	@Override
	public Comment populateAndSave(Comment comment, Class<? extends Post> postType) {

		Optional.ofNullable(comment)
				.filter(c -> Objects.nonNull(postType))
				.ifPresent(c -> c.setCommentOrigin(
						PostOrigin.getByEntityType(postType)
				));

		return commentDao.save(comment);
	}
	
	@Override
	public Comment authorizeAndSave(Comment comment) {
		log.info("Autorizujemo komentar na post tipa: "+comment.getCommentOrigin()+" sa id: "+comment.getPostId());
		if (comment.isAuthenticatedAuthor()) {
			// TODO autorizuj ako je registrovan korisnik.
		}
		return commentDao.save(comment);
	}
	
	@Override
	public int countPostComments(int postId, PostOrigin postOrigin) {
		return commentDao.countCommentsPerPost(postId, postOrigin);
	}

	@Override
	public List<Comment> commentsBundleLoad(int postId, PostOrigin postOrigin, int loaded, int size) {
		int total = countPostComments(postId, postOrigin);		
		if(loaded == total) {
			log.info("Vec su ucitani svi komentari za post:"+postId+". Nemam sta da dohvatim!");
			return null;
		}		
		size = loaded+size > total ? total-loaded : size;
		log.info("Pozivan ucitavanje grupe od "+size+" komentara za post: "+postId);
		return commentDao.getCommentsBundle(postId, postOrigin, loaded, size);
	}
	
	@Override
	public Map<Integer, Integer> getPostToCommentsCountMap(PostOrigin postOrigin) {
		return commentDao.generatePostIdToCommentsCountMap(postOrigin);
	}
}
