package com.example.springboottestapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboottestapp.dao.PostDAO;
import com.example.springboottestapp.model.Post;

@Service
public class PostService {

	@Autowired
	private PostDAO postDAO;

	@Autowired
	private UserService userService;

	public List<Post> findByUser(Integer userId) {
		return postDAO.findAllByUserId(userId);
	}

	public Post createPost(Integer userId, Post post) {
		post.setUser(userService.getUser(userId));
		return postDAO.save(post);
	}

	public Post getPost(Integer id) {
		Optional<Post> firstResult = postDAO.findById(id);

		if (firstResult.isPresent()) {
			return firstResult.get();
		}

		return null;
	}

}
