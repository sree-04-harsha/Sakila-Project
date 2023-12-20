package com.cg.sakila.serviceImplTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.enitites.Store;
import com.cg.sakila.repositories.StaffRepository;
import com.cg.sakila.repositories.StoreRepository;
import com.cg.sakila.service.StaffServiceImpl;

class StaffServiceImplTest {

	@Mock
	private StaffRepository staffRepository;

	@Mock
	private StoreRepository storeRepository;

	@InjectMocks
	private StaffServiceImpl staffService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateStaff() {
		Staff staff = new Staff();
		staff.setStaffId((int) 1);
		staff.setFirstName("John");
		staff.setLastName("Doe");
		Address address = new Address();
		address.setAddressId((int) 1);
		staff.setAddress(address);
		staff.setEmail("john.doe@example.com");
		Store store = new Store();
		store.setStoreId((int) 1);
		staff.setStore(store);
		staff.setActive(1);
		staff.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		staff.setPicture("picture.jpg");

		when(staffRepository.save(any(Staff.class))).thenReturn(staff);

		Staff result = staffService.createStaff(staff);

		assertThat(result).isEqualTo(staff);
		verify(staffRepository, times(1)).save(any(Staff.class));
	}

	@Test
	void testSearchStaffByLastName() {
		String lastName = "Doe";
		Staff staff1 = new Staff();
		staff1.setStaffId((int) 1);
		staff1.setFirstName("John");
		staff1.setLastName("Doe");
		Staff staff2 = new Staff();
		staff2.setStaffId((int) 2);
		staff2.setFirstName("Jane");
		staff2.setLastName("Doe");
		List<Staff> staffList = Arrays.asList(staff1, staff2);

		when(staffRepository.findByLastName(lastName)).thenReturn(staffList);

		List<Staff> result = staffService.searchStaffByLastName(lastName);

		assertThat(result).isEqualTo(staffList);
		verify(staffRepository, times(1)).findByLastName(lastName);
	}

	@Test
	void testSearchStaffByFirstName() {
		String firstName = "John";
		Staff staff1 = new Staff();
		staff1.setStaffId((int) 1);
		staff1.setFirstName("John");
		staff1.setLastName("Doe");
		Staff staff2 = new Staff();
		staff2.setStaffId((int) 2);
		staff2.setFirstName("John");
		staff2.setLastName("Smith");
		List<Staff> staffList = Arrays.asList(staff1, staff2);

		when(staffRepository.findByFirstName(firstName)).thenReturn(staffList);

		List<Staff> result = staffService.searchStaffByFirstName(firstName);

		assertThat(result).isEqualTo(staffList);
		verify(staffRepository, times(1)).findByFirstName(firstName);
	}

	@Test
	void testSearchStaffByEmail() {
		String email = "john.doe@example.com";
		Staff staff = new Staff();
		staff.setStaffId((int) 1);
		staff.setFirstName("John");
		staff.setLastName("Doe");
		staff.setEmail(email);

		when(staffRepository.findByEmail(email)).thenReturn(staff);

		Staff result = staffService.searchStaffByEmail(email);

		assertThat(result).isEqualTo(staff);
		verify(staffRepository, times(1)).findByEmail(email);
	}

	@Test
	void testAssignAddressToStaff() {
		int id = 1;
		Address address = new Address();
		address.setAddressId((int) 1);
		Staff staff = new Staff();
		staff.setStaffId(id);
		staff.setFirstName("John");
		staff.setLastName("Doe");
		staff.setEmail("john.doe@example.com");

		when(staffRepository.findById(id)).thenReturn(Optional.of(staff));
		when(staffRepository.save(any(Staff.class))).thenReturn(staff);

		Staff result = staffService.assignAddressToStaff(id, address);

		assertThat(result).isEqualTo(staff);
		assertThat(result.getAddress()).isEqualTo(address);
		verify(staffRepository, times(1)).findById(id);
		verify(staffRepository, times(1)).save(any(Staff.class));
	}

	@Test
	void testSearchStaffByCity() {
		String city = "New York";
		Staff staff1 = new Staff();
		staff1.setStaffId((int) 1);
		staff1.setFirstName("John");
		staff1.setLastName("Doe");
		Staff staff2 = new Staff();
		staff2.setStaffId((int) 2);
		staff2.setFirstName("Jane");
		staff2.setLastName("Smith");
		List<Staff> staffList = Arrays.asList(staff1, staff2);

		when(staffRepository.findByAddressCityCity(city)).thenReturn(staffList);

		List<Staff> result = staffService.searchStaffByCity(city);

		assertThat(result).isEqualTo(staffList);
		verify(staffRepository, times(1)).findByAddressCityCity(city);
	}
	
}
