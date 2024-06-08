package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import demo.bean.Trainer;
import demo.repository.TrainerRepo;

@Service
public class TrainerService {

	@Autowired
	TrainerRepo repo;

	public List<Trainer> getall() {
		return repo.findAll();
	}

	public void add(@RequestBody Trainer t) {
		repo.save(t);
	}

	public Trainer findbyId(@PathVariable String id) {
		return repo.findById(id).get();
	}

	public void deleteById(@PathVariable String id) {
		repo.deleteById(id);

	}
}
