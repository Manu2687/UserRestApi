package vw.practice.tdd.user2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vw.practice.tdd.user2.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

}
