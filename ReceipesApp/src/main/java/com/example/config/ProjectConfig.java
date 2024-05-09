package com.example.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cloudinary.Cloudinary;

@Configuration
public class ProjectConfig {
	
	
	@Bean
	public Cloudinary getCloudinary() {
		Map config = new HashMap();
		config.put("cloud_name", "def4uayzl");
		config.put("api_key", "748819742344231");
		config.put("api_secret", "WwZ4uRxNO8-COW9d0liLt8zr_vo");
		config.put("secure", true);
		 
		return new Cloudinary(config);
	}

}
