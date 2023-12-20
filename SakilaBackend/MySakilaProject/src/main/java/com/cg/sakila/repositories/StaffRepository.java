package com.cg.sakila.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Staff;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

	@PersistenceContext
	public EntityManager entityManager = null;

	List<Staff> findByLastName(String lastName);

	List<Staff> findByFirstName(String firstName);

	Staff findByEmail(String email);

	Staff findByAddress_Phone(String phone);

	Staff findByAddress(Address address);

	List<Staff> findByAddressCityCountryCountry(String country);

	@Query("SELECT s FROM Staff s WHERE s.address.city = :city")
	List<Staff> findByAddressCity(@Param("city") String city);

	List<Staff> findByStoreStoreId(int storeId);

	Staff findByStaffId(int managerStaffId);

	@Query("SELECT s FROM Staff s WHERE s.store.id = :storeId")
	List<Staff> findByStoreId(@Param("storeId") int storeId);

//@Query("SELECT s FROM Staff s WHERE s.store_id = :storeId")
	List<Staff> getByStoreStoreId(int storeId);

	@Query("SELECT s FROM Staff s WHERE s.id IN (SELECT st.staff.id FROM Store st)")
	List<Staff> allManagers();

//@Query("SELECT s FROM Staff s JOIN s.address a JOIN a.city c WHERE c.city = :city")
	List<Staff> findByAddressCityCity(/* @Param("city") */ String city);

}
