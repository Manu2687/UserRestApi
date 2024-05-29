package vw.practice.tdd.user2.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import vw.practice.tdd.user2.model.User;

@Repository
public interface UserDao {
	
	 List<User> findUsersWithMultipleAddressesAndPhoneNumber() ;


}
