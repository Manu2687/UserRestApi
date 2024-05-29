package vw.practice.tdd.user2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vw.practice.tdd.user2.model.PhoneNumber;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer>{

}
