package vw.practice.tdd.user2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vw.practice.tdd.user2.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
