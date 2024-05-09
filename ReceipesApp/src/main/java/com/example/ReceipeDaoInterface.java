package com.example;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bean.CountofReceipes;
import com.example.bean.Receipe;

public interface ReceipeDaoInterface {

	//Add Receipes
	public String insertReceipe(int id, String foodname, String description,
	String ingredient,
	String preparation,
	String history,
	String category, String type, MultipartFile file);
	
	//Get Receipe
	public CountofReceipes getAllReceipes(int page, int size, String sortBy, String orderBy);
	
	//Get Receipe By Category
	public CountofReceipes getAllReceipesByCategory(int page, int size, String sortBy, String orderBy, String category);
	
	//Update Receipe By Id
	public String UpdateReceipeById(int id, Receipe receipe);
}
