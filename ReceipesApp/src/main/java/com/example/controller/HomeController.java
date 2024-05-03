package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.ReceipeDaoImpl;
import com.example.bean.CountofReceipes;
import com.example.bean.Receipe;

@RestController
public class HomeController {
	
	@Autowired
	ReceipeDaoImpl receipedao;
	
	
	//add Data to Server
	@PostMapping("/addReceipe")
	public ResponseEntity<String> addNewReceipe(@RequestParam("id") int id,@RequestParam String foodname,@RequestParam String description, @RequestParam String ingredient,@RequestParam String preparation,
			@RequestParam String history,@RequestParam String category,@RequestParam String type, @RequestParam("image") MultipartFile image) {
		
		 try {
	            // Save the image to a folder
	            String fileName = image.getOriginalFilename();
	            String extension = fileName.substring(fileName.lastIndexOf('.'));
	            String newFilename = "img_" + id + extension; // Or use any other naming convention
	            String pathofdirectory = new ClassPathResource("static/images/").getFile().getAbsolutePath();
	            File imageFile = new File(pathofdirectory + newFilename);
	            image.transferTo(imageFile);
	            
	            //First Letter Capital and anothers Letter smallcase
	            String firstLetter = category.substring(0, 1).toUpperCase();
	    		String fromsecondLetter = category.substring(1).toLowerCase();
	    		String cat = firstLetter + fromsecondLetter;
	    		
	    		//First Letter Capital and anothers Letter smallcase
	            String typeletter = type.substring(0, 1).toUpperCase();
	    		String fromssecondtype = type.substring(1).toLowerCase();
	    		String types = typeletter + fromssecondtype;

	            // Save image name and ID to database
	            String message = receipedao.insertReceipe(id, foodname, description, ingredient, preparation, history, cat, types, newFilename);

	            return ResponseEntity.ok(message + id);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image.");
	        }
	}
	
	
	//getAll data from server with sortBy, by size and page size
	 @GetMapping("/getAll")
	    public ResponseEntity<CountofReceipes> getAllReceipes(@RequestParam(defaultValue = "0") int page,
	    		@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "foodname") String sortBy, @RequestParam(defaultValue = "asc") String orderBy) {
	        try {
	          CountofReceipes receipedetailwithCount = receipedao.getAllReceipes(page, size, sortBy, orderBy);
	          
	          return ResponseEntity.ok(receipedetailwithCount);
	        } catch (EmptyResultDataAccessException e) {
	            return (ResponseEntity<CountofReceipes>) ResponseEntity.notFound();
	        }
	    }
	 
	//get the category data from server with sortBy, by size and page size
	 @GetMapping("/getAll/{category}")
	 public ResponseEntity<CountofReceipes> getReceipesBycategory(@PathVariable String category, @RequestParam(defaultValue = "0") int page,
	    		@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "asc") String orderBy) {
		 try {
			//First Letter Capital and anothers Letter smallcase
	            String firstLetter = category.substring(0, 1).toUpperCase();
	    		String fromsecondLetter = category.substring(1).toLowerCase();
	    		String cat = firstLetter + fromsecondLetter;
			 
	          CountofReceipes receipedetailwithCount = receipedao.getAllReceipesByCategory(page, size, sortBy, orderBy, cat);
	          
	          return ResponseEntity.ok(receipedetailwithCount);
	        } catch (EmptyResultDataAccessException e) {
	            return (ResponseEntity<CountofReceipes>) ResponseEntity.notFound();
	        }
		 
	 }
	 
	 @PutMapping("/updatereceipeById/{id}")
		public ResponseEntity<String> UpdateHostelById(@RequestBody Receipe receipe, @PathVariable("id") int id) {
			String message = receipedao.UpdateReceipeById(id, receipe);
			return new ResponseEntity(message , HttpStatus.OK);		
		}
}