package demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import demo.bean.Hr;
import demo.bean.Jobopening;
import demo.bean.Trainer;
import demo.bean.User;
import demo.repository.HrRepo;
import demo.repository.JobopeningRepo;
import demo.repository.TrainerRepo;
import demo.repository.UserRepo;

@Service
public class JobopeningService {

	@Autowired
	JobopeningRepo jobRepo;

	@Autowired
	HrRepo hrRepo;

	@Autowired
	TrainerRepo trRepo;

	public List<Jobopening> getall() {
		return jobRepo.findAll();
	}

	public void add(Jobopening job, String emailId) {
		Hr hr = hrRepo.findById(emailId).get();

		Set<Jobopening> setJo = new HashSet<>();
		setJo.add(job);

		List<Hr> setHr = new ArrayList<>();
		setHr.add(hr);

		if (hr != null) {

			hr.setJb(setJo);
			job.setHr(setHr);

		}
		jobRepo.save(job);
		hrRepo.save(hr);
		
		System.out.println("method of hr and job is running");
		
	}

//
	public List<Jobopening> findByEmail(String id) {
		return jobRepo.findByHr_Email(id);
	}

	public void add(Trainer tr, long jobId) {
		

		Jobopening jbobj = jobRepo.findById(jobId).get();

		Set<Trainer> settr = new HashSet<>();
		settr.add(tr);

		List<Jobopening> setjb = new ArrayList<>();
		setjb.add(jbobj);

		if (tr != null) {
			jbobj.setTr(settr);
			tr.setJo(setjb);
		}
		trRepo.save(tr);
		jobRepo.save(jbobj);

		
		System.out.println("method of trainer and job is running");
	
	}
	
	
//
//	public void deleteById(int id) {
//		jobRepo.deleteById(id);
//
//	}

//	    public void referJobOpening(String email, Jobopening r) {
//	        // Fetch user details based on the email
//	        User user = userRepo.findByEmail(email)
//	            .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
//
//	        // Set the jobOpening object to the user
//	        user.setJobOpening(r);
//
//	        // Set the user object to the jobOpening
//	        r.setUser(user);
//
//	        // Save both objects
//	        UserRepo.save(user);
//	        repo.save(r);
//	    }
//	}
}
