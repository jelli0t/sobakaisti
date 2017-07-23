package org.sobakaisti.mvt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sobakaisti.mvt.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class TagDaoImpl implements TagDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Tag findOrSaveTag(Tag tag) {
		String HQL = "from Tag t where lower(t.tag) = :phrase";
		try {
			Session session = sessionFactory.getCurrentSession();
			Tag foundedTag = (Tag) session.createQuery(HQL).setString("phrase", tag.getTag().toLowerCase()).uniqueResult();
			/* ako nije pronasao tag, pravi novi */
			if(foundedTag == null) {
				System.out.println("Nije pronadjen tag ["+tag.getTag()+"] u bazi. Radim save()");
				session.save(tag);
			} else {
				System.out.println("Tag ["+foundedTag.getTag()+"] vec postoji u bazi. Vracam njega.");
				tag = foundedTag;
			}
		} catch (HibernateException e) {
			return null;
		}
		return tag;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> findTagsByHint(String hint) {
		Session session = sessionFactory.getCurrentSession();
		final String HQL = "from Tag t where lower(t.tag) like :phrase";
		List<Tag> tags = new ArrayList<Tag>();
		try {
			tags = session.createQuery(HQL).setString("phrase", hint.toLowerCase()+"%").list();
			return tags;
		} catch (Exception e) {
			return tags;
		}	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tag> findListOfTagsByIds(List<Integer> ids) {
		String HQL = "from Tag t where t.id in (:ids)";
		List<Tag> tags = new ArrayList<Tag>(ids.size());
		try {
			Session session = sessionFactory.getCurrentSession();
			tags = session.createQuery(HQL).setParameterList("ids", ids).list();			
		} catch (Exception e) {
			return null;
		}
		return tags;
	}

}
