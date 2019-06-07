package com.trakInvest.model;

import java.util.List;

public interface TwitterUserDAL {
	TwitterUser saveUser(TwitterUser twitterUser);

	List<TwitterUser> getAllUsers();

	TwitterUser findOneByName(String name);

	List<TwitterUser> findByName(String name);

	void updateOneUser(TwitterUser twitterUser);

	void deleteUser(String name);
}
