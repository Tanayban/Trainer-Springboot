package demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.bean.Jobopening;
import demo.bean.Trainer;
import demo.service.JobopeningService;
import demo.service.TrainerService;

@RestController
@CrossOrigin
@RequestMapping("/Jobopening")
public class JobopeningController {

	@Autowired
	JobopeningService serv;

	@Autowired
	TrainerService trainerserv;

	@GetMapping("/all")
	public List<Jobopening> getall() {
		return serv.getall();
	}

	@PostMapping("/add-job/{hrEmail}")
	public void add(@RequestBody Jobopening job, @PathVariable String hrEmail) {
		serv.add(job, hrEmail);
		System.out.println("after service of hr, job");
	}

	@PostMapping("/add-trainer/{jobId}")
	public void add(@RequestBody Trainer tr, @PathVariable long jobId) {
		serv.add(tr, jobId);
		System.out.println("after service of tr, job");
	}

//	@GetMapping("/byId/{id}")
//	public Jobopening findbyId(@PathVariable String id) {
//		return serv.findbyId(id);
//	}
//
//	@RequestMapping("/delete/{id}")
//	private void deleteById(@PathVariable String id) {
//		serv.deleteById(id);
//
//	}

	@GetMapping("/byId/{email}")
	public List<Jobopening> findByHr_Email( @PathVariable String email)
	{
		return serv.findByEmail(email);
	}
}
