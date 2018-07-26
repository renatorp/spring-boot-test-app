package com.example.springboottestapp.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboottestapp.exception.RestResourceNotFoundException;
import com.example.springboottestapp.model.Post;
import com.example.springboottestapp.service.PostService;

@RequestMapping("/posts")
@RestController
public class PostResource {

	@Autowired
	private PostService postService;

	@GetMapping("/{id}")
	public Post getPost(@PathVariable Integer id) {
		Post post = postService.getPost(id);

		if (post == null) {
			throw new RestResourceNotFoundException("Post of id " + id + " not found!");
		}

		return post;
	}
}
