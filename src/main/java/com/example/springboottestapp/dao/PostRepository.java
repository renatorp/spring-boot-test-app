package com.example.springboottestapp.dao;

import java.util.List;

import com.example.springboottestapp.model.Post;

public interface PostRepository {
	List<Post> findAllByUserId(Integer userId);

	void deleteAllByUserId(Integer id);
}
