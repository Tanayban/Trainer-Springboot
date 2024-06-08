package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import demo.bean.Admin;
import demo.repository.AdminRepo;

@Service
public class AdminService {

	@Autowired
	AdminRepo repo;

	public List<Admin> getall() {
		return repo.findAll();
	}

	public void add(@RequestBody Admin a) {
		repo.save(a);
	}

}
