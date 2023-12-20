package com.cg.sakila.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.enitites.Store;
import com.cg.sakila.exception.CustomException;
import com.cg.sakila.repositories.StaffRepository;

@Service
public class StaffServiceImpl implements StaffService {

	private StaffRepository staffRepository;

	@Autowired
	public StaffServiceImpl(StaffRepository staffRepository) {
		super();
		this.staffRepository = staffRepository;
	}

	@Override
	public List<Staff> searchStaffByLastName(String lastName) throws CustomException {
		List<Staff> list = staffRepository.findByLastName(lastName);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Staff are not found..");
		}
	}

	@Override
	public List<Staff> searchStaffByFirstName(String firstName) throws CustomException {
		List<Staff> list = staffRepository.findByFirstName(firstName);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Staff are not found.. ");
		}
	}

	@Override
	public Staff searchStaffByEmail(String email) throws CustomException {
		if (email == null || email.isEmpty()) {
			throw new CustomException("Email cannot be null or empty.");
		}

		return staffRepository.findByEmail(email);
	}

	@Override
	public Staff assignAddressToStaff(int id, Address address) throws CustomException {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setAddress(address);
			return staffRepository.save(staff);
		} else {
			throw new CustomException("No staff found with the id: " + id);
		}
	}

	@Override
	public Staff searchStaffByAddress(Address address) {
		return staffRepository.findByAddress(address);
	}

	@Override
	public List<Staff> searchStaffByCity(String city) throws CustomException {

		List<Staff> list = staffRepository.findByAddressCityCity(city);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Staff are not found.. ");
		}
	}

	@Override
	public List<Staff> searchStaffByCountry(String country) throws CustomException {
		List<Staff> list = staffRepository.findByAddressCityCountryCountry(country);
		if (!list.isEmpty()) {
			return list;
		} else {
			throw new CustomException("Staff are not found.. ");
		}
	}

	@Override
	public Staff searchStaffByPhone(String phone) throws CustomException {
		if (phone == null || phone.isEmpty()) {
			throw new CustomException("Phone number cannot be null or empty.");
		}

		return staffRepository.findByAddress_Phone(phone);
	}

	@Override
	public Staff updateStaffFirstName(int id, String firstName) throws CustomException {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setFirstName(firstName);
			return staffRepository.save(staff);
		} else {
			throw new CustomException("No staff found with the id: " + id);
		}
	}

	@Override
	public Staff updateStaffLastName(int id, String lastName) throws CustomException {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setLastName(lastName);
			return staffRepository.save(staff);
		} else {
			throw new CustomException("No staff found with the id: " + id);
		}
	}

	@Override
	public Staff updateStaffEmail(int id, String email) throws CustomException {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setEmail(email);
			return staffRepository.save(staff);
		} else {
			throw new CustomException("No staff found with the id: " + id);
		}
	}

	@Override
	public Staff assignStoreToStaff(int id, Store store) throws CustomException {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.setStore(store);
			return staffRepository.save(staff);
		} else {
			throw new CustomException("No staff found with the id: " + id);
		}
	}

	@Override
	public Staff updateStaffPhone(int id, String phone) throws CustomException {
		Staff staff = staffRepository.findById(id).orElse(null);
		if (staff != null) {
			staff.getAddress().setPhone(phone);
			return staffRepository.save(staff);
		} else {
			throw new CustomException("No staff found with the id: " + id);
		}

	}

	@Override
	public void deleteStaff(int id) {
		staffRepository.deleteById(id);
	}

	@Override
	public Staff getStaffById(int staffId) throws CustomException {
		Optional<Staff> optionalStaff = staffRepository.findById(staffId);
		return optionalStaff.orElseThrow(() -> new CustomException("No staff found with the staffId: " + staffId));
	}

	@Override
	public Staff updateStaff(Staff staff) {
		return staffRepository.save(staff);
	}

	@Override
	public Staff addStaff(Staff staff) {
		return staffRepository.save(staff);
	}

	@Override
	public Staff createStaff(Staff staff) {
		return staffRepository.save(staff);
	}

	@Override
	public List<Staff> fetchAll() {
		return staffRepository.findAll();
	}

}
