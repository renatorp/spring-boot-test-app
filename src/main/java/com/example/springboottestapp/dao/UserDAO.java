package com.example.springboottestapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboottestapp.model.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
}
