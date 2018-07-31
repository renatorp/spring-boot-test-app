package com.example.springboottestapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboottestapp.dao.PostDAO;
import com.example.springboottestapp.dao.UserDAO;
import com.example.springboottestapp.model.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private PostDAO postDAO;

	public User createUser(User user) {
		return userDAO.save(user);
	}

	public List<User> findUsers() {
		return userDAO.findAll();
	}

	public User getUser(Integer id) {
		Optional<User> user = userDAO.findById(id);

		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	public User deleteUser(Integer id) {

		User user = getUser(id);

		if (user != null) {

			// jpa 2.1 now allowing criteria delete with joins
			// avoid this in code to be released to production.
			postDAO.findAllByUserId(id).forEach(postDAO::delete);

			userDAO.delete(user);

			return user;
		}

		return null;
	}
}
