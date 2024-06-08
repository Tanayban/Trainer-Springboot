package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import demo.bean.Hr;
import demo.repository.HrRepo;

@Service
public class HrService {

	@Autowired
	HrRepo repo;

	public List<Hr> getall() {
		return repo.findAll();
	}

	public void add(@RequestBody Hr h) {
		repo.save(h);
	}

	public Hr findbyId(@PathVariable String id) {
		return repo.findById(id).get();
	}

	public void deleteById(@PathVariable String id) {
		repo.deleteById(id);

	}
}
