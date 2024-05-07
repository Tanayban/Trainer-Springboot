package com.example.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
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
	
	@Value("${project.image}")
	private String path;
	
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	
	
	
	//add Data to Server
	@PostMapping("/addReceipe")
	public ResponseEntity<String> addNewReceipe(@RequestParam("id") int id,@RequestParam String foodname,@RequestParam String description, @RequestParam String ingredient,@RequestParam String preparation,
			@RequestParam String history,@RequestParam String category,@RequestParam String type, @RequestParam("image") MultipartFile image) {
		
		 try {
	            // Save the image to a folder
	            String fileName = image.getOriginalFilename();
	            String extension = fileName.substring(fileName.lastIndexOf('.'));
	            String newFilename = "img_" + id + extension; // Or use any other naming convention
	            
	         // Ensure the target directory exists
	            Resource resource = resourceLoader.getResource("classpath:" + "static/images");
	            File folder = resource.getFile();
	            String pathasJar = folder.getAbsolutePath();
	            
	            //try
	            InputStream dbAsStream = resource.getInputStream(); // <-- this is the difference
	            BufferedReader reader = new BufferedReader(new InputStreamReader(dbAsStream));
	            String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
	            
	            String filePath = contents+ "/" +newFilename;
	            
	            
	            Files.copy(image.getInputStream(), Paths.get(filePath));
	           
	            
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
	    		@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "foodname") String sortBy, @RequestParam(defaultValue = "asc") String orderBy) throws IOException {
	        try {
	        	
	        	//1
	        	Resource resource = resourceLoader.getResource("classpath:/" + "static/images");
	            InputStream dbAsStream = resource.getInputStream(); // <-- this is the difference
	            
	            
	            //2
	        	InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/images");
	        	
	        	//3
	        	ClassPathResource resources = new ClassPathResource("static/images");
	        	InputStream inputStreams = resources.getInputStream();
	        	
	        	//4
	        	
	        	
	        	
//	            BufferedReader reader = new BufferedReader(new InputStreamReader(dbAsStream));
//	            String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));
	            
	            
	            String path = new String(inputStream.readAllBytes());
	            String path2 = new String(dbAsStream.readAllBytes());
	            String path3 = new String(inputStreams.readAllBytes());

		         
	            System.out.println("Problem 1 " + path);
		        System.out.println("Problem 2: "+ path2);
		        System.out.println("Problem 3: "+ path3);
		        
	        
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
