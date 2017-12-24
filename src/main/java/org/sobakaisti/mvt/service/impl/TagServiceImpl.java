package org.sobakaisti.mvt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.sobakaisti.mvt.dao.TagDao;
import org.sobakaisti.mvt.models.Post;
import org.sobakaisti.mvt.models.Tag;
import org.sobakaisti.mvt.service.TagService;
import org.sobakaisti.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	private TagDao tagDao;
	
	@Override
	public Tag findById(int id) {
		return tagDao.findById(id);
	}

	@Override
	public Tag createAndSaveTagFromString(String input) {
		if(input != null && !input.isEmpty()) {
			input = input.trim().toLowerCase();
			final String slug = StringUtil.makeSlugFromTitle(input);
			Tag tag = new Tag();
			tag.setTag(input);
			tag.setSlug(slug);
			return tag;
		}else {
			return null;
		}		
	}

	@Override
	public Tag findOrCreateTagFromPhrase(String phrase) {
		if(phrase != null && !phrase.isEmpty()) {
			phrase = phrase.trim();
			final String slug = StringUtil.makeSlug(phrase);
			Tag tag = new Tag();
			tag.setTag(phrase);
			tag.setSlug(slug);
			return tagDao.findOrSaveTag(tag);
		}else {
			return null;
		}		
	}

	@Override
	public List<Tag> findListOfTagsByHint(String hint) {
		hint = hint.trim();
		return tagDao.findTagsByHint(hint);
	}

	@Override
	public List<Tag> findListOfTagsByIdsArray(int[] ids) {
		List<Integer> tagIds = new ArrayList<Integer>(ids.length);
		for(int i=0; i < ids.length; i++) {
			tagIds.add(ids[i]);
		}
		return tagIds.size() > 0 ? tagDao.findListOfTagsByIds(tagIds) : null;
	}

	

}
