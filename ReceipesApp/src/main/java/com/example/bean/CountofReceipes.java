package com.example.bean;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class CountofReceipes {
	long count;
	List<Map<String, String>> Receipes;
	
	public CountofReceipes() {
		// TODO Auto-generated constructor stub
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public List<Map<String, String>> getReceipes() {
		return Receipes;
	}

	public void setReceipes(List<Map<String, String>> receipes) {
		Receipes = receipes;
	}

	@Override
	public String toString() {
		return "CountofReceipes [count=" + count + ", Receipes=" + Receipes + "]";
	}

	public CountofReceipes(long count, List<Map<String, String>> receipes) {
		this.count = count;
		Receipes = receipes;
	}
	
}
