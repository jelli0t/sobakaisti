package org.sobakaisti.mvt.service;

import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.util.PostRequest;
import org.springframework.stereotype.Service;

@Service
@Qualifier("articlePostFactory")
public class ArticlePostFactory extends PostFactory{

	@Override
	public Post processPostRequest(PostRequest postRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
