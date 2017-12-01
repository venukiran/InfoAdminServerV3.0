package com.safe.test;

import org.springframework.web.client.RestTemplate;

import antlr.collections.List;

public class TestRestService {

	public static void main(String args[]){
		RestTemplate template = new RestTemplate();
		List lst = template.getForObject("http://localhost:8080/locations", List.class);
		System.out.println(lst);
	}
	
}
