package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import demo.bean.Company;
import demo.repository.CompanyRepo;

@Service
public class CompanySevice {

	@Autowired
	CompanyRepo repo;

	public List<Company> getall() {
		return repo.findAll();
	}

	public void add(@RequestBody Company u) {
		repo.save(u);
	}

	public Company findbyId(@PathVariable String id) {
		return repo.findById(id).get();
	}

	public void deleteById(@PathVariable String i) {
		repo.deleteById(i);

	}
	
	public Company findbyCompanymail(@PathVariable String companymail) {
		return repo.findByemail(companymail);
	}

}
