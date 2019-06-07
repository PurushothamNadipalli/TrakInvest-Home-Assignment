package com.trakInvest.db.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.trakInvest.model.TwitterUser;
import com.trakInvest.model.TwitterUserDAL;

@Component
public class UserDALImplUtility {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	TwitterUserDAL twitterUserDAL;

	public ResponseEntity<String> createUser(String  userName) {
		LOG.debug("Creating Users");
		TwitterUser twitterUser=new TwitterUser();
		twitterUser.setName(userName);
		twitterUser.setFollowers(new ArrayList<String>());
		twitterUserDAL.saveUser(twitterUser);

		return new ResponseEntity<String>("User Created Successfully", HttpStatus.CREATED);
	}

	public ResponseEntity<?> getAllUsers() {
		LOG.debug("get all Users");
		List<TwitterUser> data = null;

		data = twitterUserDAL.getAllUsers();

		return new ResponseEntity<List<TwitterUser>>(data, HttpStatus.OK);
	}

	public ResponseEntity<?> findByName(String name) {
		LOG.debug("Getting user with name {}.", name);
		List<TwitterUser> data = null;

		data = twitterUserDAL.findByName(name);

		if (data == null || data.isEmpty()) {
			return new ResponseEntity<String>("User does not exist with the given name", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<TwitterUser>>(data, HttpStatus.OK);
	}

	public ResponseEntity<?> getFollowers(String name) {
		LOG.debug("Getting Followers");
		List<TwitterUser> data = null;
		data = twitterUserDAL.findByName(name);

		if (data == null || data.isEmpty()) {
			return new ResponseEntity<String>("User does not exist with the given name", HttpStatus.NOT_FOUND);
		}
		if(data.get(0).getFollowers().isEmpty()) {
			return new ResponseEntity<String>("No Followers for the User "+ name, HttpStatus.OK);
		}
		return new ResponseEntity<List<String>>(data.get(0).getFollowers(), HttpStatus.OK);
	}

	public ResponseEntity<?> UpdateFollowers(String username, String followerNmae) {
		LOG.debug("Following User with FollowerName");
		List<TwitterUser> data = null;
		data = twitterUserDAL.findByName(username);
		TwitterUser twitterUser = data.get(0);
		twitterUser.getFollowers().add(followerNmae);
		twitterUserDAL.saveUser(twitterUser);
		return new ResponseEntity<String>("User "+username +" started following " + followerNmae, HttpStatus.OK);
	}

	public ResponseEntity<?> UnFollowUser(String username, String followerNmae) {
		LOG.debug("UnFollow  User with FollowerName");
		List<TwitterUser> data = null;
		data = twitterUserDAL.findByName(username);

		TwitterUser twitterUser = data.get(0);
		twitterUser.getFollowers().removeIf(u -> u.equals(followerNmae));
		twitterUserDAL.saveUser(twitterUser);

		return new ResponseEntity<String>(followerNmae + "  is unfollowing " + username, HttpStatus.OK);

	}

	public ResponseEntity<?> deleteUser(String name) {
		LOG.debug("Deleting User.");
		twitterUserDAL.deleteUser(name);

		return new ResponseEntity<String>("User Delete successfully", HttpStatus.OK);
	}
	
}
