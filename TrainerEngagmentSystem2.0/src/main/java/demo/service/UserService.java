package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import demo.bean.*;
import demo.bean.User;
import demo.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo repo;

	public List<User> getall() {
		return repo.findAll();
	}

	public void add(@RequestBody User u) {
		repo.save(u);
	}

	public User findbyId(@PathVariable String id) {
		return repo.findById(id).get();
	}

	public void deleteById(@PathVariable String id) {
		repo.deleteById(id);

	}

	public String login(String email, String pass) {
		User user = repo.findByEmailAndPassword(email, pass);
		if (user instanceof Trainer)
			return "TRAINER";
		else if (user instanceof Admin)
			return "ADMIN";
		else if (user instanceof Company)
			return "COMPANY";
		else if (user instanceof Hr)
			return "HR";
		else
			return "Login Unsucessful";
	}
}
