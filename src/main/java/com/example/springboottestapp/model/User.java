package com.example.springboottestapp.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class User {

	@JsonProperty
	private Integer id;

	@JsonProperty
	@Size(min = 2, message = "Name should have at least 2 characters!")
	private String name;

	@JsonProperty
	private String surname;

	@JsonProperty
	@Past(message = "Birth date must be in the past!")
	private Date birthDate;

	private List<Post> posts;

	public User() {
		super();
	}

	public User(Integer id, String name, String surname, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + "]";
	}

}
