package com.trakInvest.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TwitterUser")
public class TwitterUser {

	@Id
	private String id;
	@Indexed(unique = true)
	private String name;
	private List<String> followers;

	public TwitterUser(String name) {
		super();
		this.name = name;
	}

	public TwitterUser() {

	}

	@Override
	public String toString() {
		return "TwitterUser [id=" + id + ", name=" + name + ", followers=" + followers + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFollowers() {
		return followers;
	}

	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}

}
