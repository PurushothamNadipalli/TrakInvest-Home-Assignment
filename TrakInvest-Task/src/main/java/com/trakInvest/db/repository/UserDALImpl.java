package com.trakInvest.db.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.trakInvest.model.TwitterUser;
import com.trakInvest.model.TwitterUserDAL;

@Service
public class UserDALImpl implements TwitterUserDAL {

	private final MongoTemplate mongoTemplate;

	@Autowired
	public UserDALImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public TwitterUser saveUser(TwitterUser twitterUser) {

		mongoTemplate.save(twitterUser);

		return twitterUser;
	}

	public List<TwitterUser> getAllUsers() {

		return mongoTemplate.findAll(TwitterUser.class);
	}

	public TwitterUser findOneByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.findOne(query, TwitterUser.class);

	}

	public List<TwitterUser> findByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return mongoTemplate.find(query, TwitterUser.class);
	}

	public void updateOneUser(TwitterUser twitterUser) {
		mongoTemplate.save(twitterUser);
	}

	public void deleteUser(String  name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		mongoTemplate.findAndRemove(query, TwitterUser.class);
	}

}
