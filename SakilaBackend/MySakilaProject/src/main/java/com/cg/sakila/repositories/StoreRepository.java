package com.cg.sakila.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.sakila.enitites.Customer;
import com.cg.sakila.enitites.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {

	List<Store> findByAddressCityCity(String city);

	List<Store> findByAddressCityCountryCountry(String countryName);

	Store findByAddressPhone(String phone);

	Store findByStoreId(int id);

	@Query("SELECT c FROM Customer c JOIN Store s ON c.storeId = s.storeId WHERE s.storeId = :storeId")
	List<Customer> findCustomersByStoreId(@Param("storeId") int storeId);

	@Query("SELECT s.storeId, s.address.addressId, s.lastUpdate, s.staff.staffId FROM Store s")
	Map<Object, Object> findAllOfMyStore();
}