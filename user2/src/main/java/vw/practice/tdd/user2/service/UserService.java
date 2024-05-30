package vw.practice.tdd.user2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vw.practice.tdd.user2.dao.UserDao;
import vw.practice.tdd.user2.model.User;
import vw.practice.tdd.user2.repository.UserRepository;

@Service
public class UserService {
	
	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private UserDao userDao;

	    public User saveUser(User user) {
	        return userRepository.save(user);
	    }

	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    public User getUserById(int id) {
	        return userRepository.findById(id).orElse(null);
	    }

	    public void deleteUser(int id) {
	        userRepository.deleteById(id);
	    }
	    
	    public User updateUser(int id, User user) {
	    	Optional<User> u = userRepository.findById(id);
	    	
	    	if(u.isPresent()) {
	    		User u2 = u.get();
	    		u2.setEmail(user.getEmail());
	    		u2.setPhoneNumbers(user.getPhoneNumbers());
	    		u2.setAddresses(user.getAddresses());
	    		u2.setName(user.getName());
	    		return userRepository.save(u2);
	    	}
	    	return userRepository.save(user);
	    }

	    public List<User> getUsersWithMultipleAddressesAndPhoneNumber() {
	        return userDao.findUsersWithMultipleAddressesAndPhoneNumber();
	    }
	


}
