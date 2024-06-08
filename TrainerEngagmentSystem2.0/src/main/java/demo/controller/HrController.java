package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.bean.Company;
import demo.bean.Hr;
import demo.repository.HrRepo;
import demo.service.CompanySevice;
import demo.service.HrService;

@RestController
@CrossOrigin
@RequestMapping("/Hr")
public class HrController {

	@Autowired
	HrService hr;
	
	@Autowired
	CompanySevice companyserv;

	@GetMapping("/all")
	public List<Hr> getall() {
		return hr.getall();
	}
	
	

	@PostMapping("/add")
	public void add(@RequestBody Hr h) {
		 if (h.getCompany() != null && h.getCompany().getEmail() != null) {
	            // Fetch the existing company
	            Company existingCompany = companyserv.findbyId(h.getCompany().getEmail());
	            if (existingCompany == null) {
	                // Save the Company entity if it does not exist
	                existingCompany = h.getCompany();
	                companyserv.add(existingCompany);
	            }
	            h.setCompany(existingCompany); // Set the managed Company entity to the HR object
	        }
	        hr.add(h);
	}

	@GetMapping("/byId/{id}")
	public Hr findbyId(@PathVariable String id) {
		
		return hr.findbyId(id);
	}

	@DeleteMapping("/delete/{id}")
	private void deleteById(@PathVariable String id) {
		hr.deleteById(id);

	}

}
