package com.example.springboottestapp.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.springboottestapp.model.User;

@Service
public class UserService {

private int idSeq = 0;
	
	private Map<Integer, User> users = new HashMap<>();

	{
		users.put(++idSeq, new User(1, "Haf", "Thorleifsson",
				Date.from(LocalDate.of(1980, 11, 3).atStartOfDay(ZoneId.systemDefault()).toInstant())));
		
		users.put(++idSeq, new User(2, "Hreitharr", "Gautisson",
				Date.from(LocalDate.of(1970, 7, 7).atStartOfDay(ZoneId.systemDefault()).toInstant())));
		
		users.put(++idSeq, new User(3, "Hallgerd", "Iogæirdottir",
				Date.from(LocalDate.of(1940, 1, 24).atStartOfDay(ZoneId.systemDefault()).toInstant())));
	}
	
	public void createUser(User user) {
		user.setId(++idSeq);
		users.put(user.getId(), user);
	}
	
	public List<User> findUsers() {
		return new ArrayList<>(users.values());
	}
	
	public User getUser(Integer id) {
		return users.get(id);
	}
	
	public User deleteUser(Integer id) {
		return users.remove(id);
	}
}
