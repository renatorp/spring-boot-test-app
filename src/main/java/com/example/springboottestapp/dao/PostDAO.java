package com.example.springboottestapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboottestapp.model.Post;

public interface PostDAO extends JpaRepository<Post, Integer>, PostRepository {

}
