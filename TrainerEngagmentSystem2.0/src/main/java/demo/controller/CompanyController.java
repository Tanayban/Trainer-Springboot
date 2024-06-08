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
import demo.repository.CompanyRepo;
import demo.service.CompanySevice;

@RestController
@CrossOrigin
@RequestMapping("/Company")
public class CompanyController {

	@Autowired
	CompanySevice comp;

	@GetMapping("/all")
	public List<Company> getall() {
		return comp.getall();
	}

	@PostMapping("/add")
	public void add(@RequestBody Company u) {
		comp.add(u);
	}

	@GetMapping("/byId/{id}")
	public Company findbyId(@PathVariable String id) {
		return comp.findbyId(id);
	}

	@DeleteMapping("/delete/{id}")
	private void deleteById(@PathVariable String id) {
		comp.deleteById(id);

	}

}
