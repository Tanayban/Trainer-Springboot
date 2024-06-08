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

import demo.bean.User;
import demo.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/User")
public class UserController {

	@Autowired
	UserService serv;

	@GetMapping("/all")
	public List<User> getall() {
		return serv.getall();
	}

	@PostMapping("/add")
	public void add(@RequestBody User u) {
		serv.add(u);
	}

	@GetMapping("/byId/{id}")
	public User findbyId(@PathVariable String id) {
		return serv.findbyId(id);
	}

	@DeleteMapping("/delete/{id}")
	private void deleteById(@PathVariable String id) {
		serv.deleteById(id);

	}

	@GetMapping("/login/{email}/{pass}")
	public String login(@PathVariable String email, @PathVariable String pass) {
		return serv.login(email, pass);
	}

}
