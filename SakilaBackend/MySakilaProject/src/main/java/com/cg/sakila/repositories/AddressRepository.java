package com.cg.sakila.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.sakila.enitites.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
