package vw.practice.tdd.user2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import vw.practice.tdd.user2.dao.UserDao;
import vw.practice.tdd.user2.model.Address;
import vw.practice.tdd.user2.model.PhoneNumber;
import vw.practice.tdd.user2.model.User;
import vw.practice.tdd.user2.repository.UserRepository;

public class UserServiceTest {
	
	@Mock private UserRepository userRepo;
	@Mock private UserDao userDao;
	@InjectMocks private UserService userService;
	
	PhoneNumber p1 = new PhoneNumber("9599142687");
	PhoneNumber p2 = new PhoneNumber("8130375904");
	
	Address a1 = new Address("Gzbd");
	Address a2 = new Address("Delhi");
	
	User user = new User("Manu Chahar","m.chahar2687@gmail.com",List.of(p1,p2),List.of(a1,a2));
	User user2 = new User("Mansi Agarwal","m8@gmail.com",List.of(p2),List.of(a1,a2));
	User user3 = new User("Sahil Chahar","chaharraj9@gmail.com",List.of(p2),List.of(a1));
	
	ArrayList<User> userList ;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		userList = new ArrayList<>();
	}
	
	@Test
	void shouldReturnUserWhenAdded() {
		when(userService.saveUser(user)).thenReturn(user);
		User savedUser = userService.saveUser(user);
		assertEquals(user,savedUser);
		
	}
	
	@Test
	void shouldReturnAllUsers() {
		when(userRepo.findAll()).thenReturn(List.of(user, user2, user3));
        List<User> allUsers = userService.getAllUsers();
        assertEquals(3, allUsers.size());
        assertTrue(allUsers.contains(user));
        assertTrue(allUsers.contains(user2));
        assertTrue(allUsers.contains(user3));;
		
	}
	
	@Test
	void shouldReturnUserWhenIdIsGiven() {
		int id=1;
		when(userRepo.findById(id)).thenReturn(Optional.of(user));
		User foundUser = userService.getUserById(id);
		assertEquals(user,foundUser);
	}
	
	
	
	@Test
	void shouldDeleteUserWhenIdIsPresent() {
		int id=1;
		doNothing().when(userRepo).deleteById(id);
		userService.deleteUser(id);
		verify(userRepo,times(1)).deleteById(id);
	}
	
	
	
	
	
	
	
	


}
