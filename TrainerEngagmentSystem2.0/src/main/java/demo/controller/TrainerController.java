package demo.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import demo.bean.Trainer;
import demo.service.TrainerService;

@RestController
@CrossOrigin
@RequestMapping("/Trainer")
public class TrainerController {

	@Autowired
	private Cloudinary cloudinary;

	@Autowired
	TrainerService serv;

	@GetMapping("/all")
	public List<Trainer> getall() {
		return serv.getall();
	}

	@PostMapping("/add")
	public void add(@RequestParam String name, @RequestParam MultipartFile photo, @RequestParam String gender,
			@RequestParam String about, @RequestParam String skills, @RequestParam String experience,
			@RequestParam String education, @RequestParam long phonenumber, @RequestParam String password,
			@RequestParam String email, @RequestParam String location) {

		Map data = null;
		try {
			data = this.cloudinary.uploader().upload(photo.getBytes(), Map.of());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String imagename = data.get("url").toString();

		Trainer t = new Trainer();
		t.setName(name);
		t.setAbout(about);
//			t.setBirthdate(birthdate);
		t.setEducation(education);
		t.setEmail(email);
		t.setExperience(experience);
		t.setGender(gender);
		t.setPassword(password);
		t.setPhonenumber(phonenumber);
		t.setPhoto(imagename);
//			t.setResume(resume);
		t.setSkills(skills);
		t.setLocation(location);

		serv.add(t);
	}

	@GetMapping("/byId/{id}")
	public Trainer findbyId(@PathVariable String id) {
		return serv.findbyId(id);
	}

	@DeleteMapping("/delete/{id}")
	private void deleteById(@PathVariable String id) {
		serv.deleteById(id);

	}
}
