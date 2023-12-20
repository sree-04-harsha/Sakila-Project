package com.cg.sakila.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sakila.enitites.Address;
import com.cg.sakila.enitites.Staff;
import com.cg.sakila.enitites.Store;
import com.cg.sakila.service.StaffServiceImpl;

@RestController
@RequestMapping("/api/v1/staff")
@CrossOrigin(origins = "http://localhost:4200")
public class StaffController {

	private final StaffServiceImpl staffService;

	@Autowired
	public StaffController(StaffServiceImpl staffService) {
		super();
		this.staffService = staffService;
	}

	// done
	@PostMapping("/post")
	public ResponseEntity<String> addStaff(@RequestBody Staff staff) {
		staffService.addStaff(staff);
		return new ResponseEntity<>("Record Created Successfully", HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public List<Staff> fetchAll() {
		return staffService.fetchAll();
	}

	// done
	@GetMapping("/lastname/{ln}")
	public List<Staff> searchStaffByLastName(@PathVariable String ln) {
		return staffService.searchStaffByLastName(ln);
	}

	// done
	@GetMapping("/firstname/{fn}")
	public List<Staff> searchStaffByFirstName(@PathVariable String fn) {
		return staffService.searchStaffByFirstName(fn);
	}

	// done
	@GetMapping("/email/{email}")
	public Staff searchStaffByEmail(@PathVariable String email) {
		return staffService.searchStaffByEmail(email);
	}

	@PutMapping("/{id}/address")
	public ResponseEntity<Staff> assignAddressToStaff(@PathVariable("id") int staffId, @RequestBody Address address) {
		// Get the staff by ID
		Staff staff = staffService.getStaffById(staffId);
		// Assign the address to the staff
		staff.setAddress(address);
		// Update the staff
		Staff updatedStaff = staffService.updateStaff(staff);
		return new ResponseEntity<>(updatedStaff, HttpStatus.OK);
	}

	// done
	@GetMapping("/city/{city}")
	public List<Staff> searchStaffByCity(@PathVariable String city) {
		return staffService.searchStaffByCity(city);
	}

	// done
	@GetMapping("/country/{country}")
	public List<Staff> searchStaffByCountry(@PathVariable String country) {
		return staffService.searchStaffByCountry(country);
	}

	// done
	@GetMapping("/phone/{phone}")
	public Staff searchStaffByPhone(@PathVariable String phone) {
		return staffService.searchStaffByPhone(phone);
	}

	// done
	@PutMapping("/update/fn/{id}")
	public Staff updateStaffFirstName(@PathVariable int id, @RequestBody Staff staff) {
		// Update the first name of the staff object
		staff.setFirstName(staff.getFirstName());
		// Pass the updated staff object to the service method
		return staffService.updateStaffFirstName(id, staff.getFirstName());
	}

	// done
	@PutMapping("/update/ln/{id}")
	public Staff updateStaffLastName(@PathVariable int id, @RequestBody Staff staff) {
		// Update the last name of the staff object
		staff.setLastName(staff.getLastName());

		// Pass the updated staff object to the service method
		return staffService.updateStaffLastName(id, staff.getLastName());
	}

	// done
	@PutMapping("/update/email/{id}")
	public Staff updateStaffEmail(@PathVariable int id, @RequestBody Staff staff) {
		// Update the email of the staff object
		staff.setEmail(staff.getEmail());

		// Pass the updated staff object to the service method
		return staffService.updateStaffEmail(id, staff.getEmail());
	}

	@PutMapping("/update/store/{id}")
	public Staff assignStoreToStaff(@PathVariable int id, @RequestBody Store store) {
		return staffService.assignStoreToStaff(id, store);
	}

	@PutMapping("/update/phone/{id}")
	public Staff updateStaffPhone(@PathVariable int id, @RequestBody String phone) {
		return staffService.updateStaffPhone(id, phone);
	}

}
