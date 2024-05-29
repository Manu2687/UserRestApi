package vw.practice.tdd.user2.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import vw.practice.tdd.user2.dao.UserDao;
import vw.practice.tdd.user2.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<User> findUsersWithMultipleAddressesAndPhoneNumber() {
		String sql = "SELECT u.user_id, u.email, u.name " +
                "FROM users u " +
                "JOIN phone_numbers p ON u.user_id = p.user_id_num " +
                "JOIN addresses a ON u.user_id = a.user_id_num " +
                "GROUP BY u.user_id, u.email, u.name " +
                "HAVING COUNT(DISTINCT a.add_id) > 1 AND COUNT(DISTINCT p.ph_no_id) > 1";
		
		
		
		
		
		return jdbcTemplate.query(sql, new UserRowMapper());
	}
	

}
