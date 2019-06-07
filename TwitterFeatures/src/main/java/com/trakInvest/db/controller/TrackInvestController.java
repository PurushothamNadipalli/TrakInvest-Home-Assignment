package com.trakInvest.db.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trakInvest.db.repository.UserDALImplUtility;
import com.trakInvest.model.TwitterUser;
import com.trakInvest.model.TwitterUserDAL;

@RestController
@RequestMapping(value = "/trackInvest")
public class TrackInvestController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	TwitterUserDAL twitterUserDAL;
	@Autowired
	UserDALImplUtility userDALImplUtility;

	@PostMapping(value = "/createUser")
	public ResponseEntity<String> create(@RequestParam String UserName) {
		LOG.debug("createUser started");
		return userDALImplUtility.createUser(UserName);

	}

	/**
	 * Method to fetch all employees from the db.
	 * 
	 * @return
	 */
	@GetMapping(value = "/getallUsers")
	public ResponseEntity<?> getAllUsers() {
		LOG.debug("Getting all Users started.");
		return userDALImplUtility.getAllUsers();
	}

	/**
	 * Method to fetch employee by id.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/getUserByName/{name}")
	public ResponseEntity<?> getUserByName(@PathVariable(value = "name") String name) {
		LOG.debug("Getting  Users by name started.");
		return userDALImplUtility.findByName(name);
	}

	@PutMapping(value = "/followUser")
	public ResponseEntity<?> UpdateFollowers(@RequestParam String UserName, @RequestParam String followerNmae) {
		LOG.debug("Updating  Followers started");

		return userDALImplUtility.UpdateFollowers(UserName, followerNmae);

	}

	@PutMapping(value = "/unFollow")
	public ResponseEntity<?> UnFollowUser(@RequestParam String UserName, @RequestParam String followerNmae) {
		LOG.debug("un-follow user started  Followers started");

		return userDALImplUtility.UnFollowUser(UserName, followerNmae);

	}

	@DeleteMapping(value = "/deleteUser")
	public ResponseEntity<?> deleteUser(@RequestParam String UserName) {
		LOG.debug("Deleting User by nmae");

		return userDALImplUtility.deleteUser(UserName);

	}
	@GetMapping(value = "/getFollowers/{name}")
	public ResponseEntity<?> getFollowersByUser(@PathVariable(value = "name") String name) {
		LOG.debug("Getting Followers");
		return userDALImplUtility.getFollowers(name);
	}
}