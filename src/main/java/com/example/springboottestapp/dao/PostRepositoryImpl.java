package com.example.springboottestapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.example.springboottestapp.model.Post;
import com.example.springboottestapp.model.User;

@Repository
public class PostRepositoryImpl implements PostRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Post> findAllByUserId(Integer userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Post> cq = cb.createQuery(Post.class);

		Root<Post> from = cq.from(Post.class);

		Join<Object, User> join = from.join("user");

		cq.where(cb.equal(join.get("id"), userId));
		CriteriaQuery<Post> select = cq.select(from);

		TypedQuery<Post> typedQuery = em.createQuery(select);
		return typedQuery.getResultList();
	}

	/**
	 * NOT WORKING
	 * 
	 * JPA 2.1 - Não pode der joins em criteria update/delete - Não é possível fazer
	 * dois mapeamento para a mesma coluna (talvez seja o hibernate) - Retorno de
	 * query apenas a partir de TypedQuery
	 */
	@Override
	public void deleteAllByUserId(Integer userId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<Post> cq = cb.createCriteriaDelete(Post.class);

		Root<Post> from = cq.from(Post.class);

		Subquery<User> subquery = cq.subquery(User.class);
		Root<User> sqFrom = subquery.from(User.class);
		subquery.select(sqFrom);
		subquery.where(cb.equal(sqFrom.get("id"), userId), cb.equal(sqFrom, from.get("user")));

		cq.where(from.get("user").in(subquery));

		em.createQuery(cq).executeUpdate();
	}

}
