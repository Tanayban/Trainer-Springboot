package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.bean.Admin;
import demo.service.AdminService;

@RestController
@CrossOrigin
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	AdminService serv;

	@GetMapping("/all")
	public List<Admin> getall() {
		return serv.getall();
	}

	@PostMapping("/add")
	public void add(@RequestBody Admin a) {
		serv.add(a);
	}

}
