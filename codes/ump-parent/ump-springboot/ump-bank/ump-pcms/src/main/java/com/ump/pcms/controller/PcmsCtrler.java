package com.ump.pcms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PcmsCtrler {
	@Autowired
	private RestTemplate restTemplate;

	//private String userServicePath = "http://ump-uias/";

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getUser")
	public Map<String,Object> getUser(@RequestParam Integer id) {
		 Map<String,Object> data = new HashMap<>();
//		ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(this.userServicePath + "getUser/" + id,
//				String.class);
		 data = restTemplate.getForObject("http://ump-uias/getUser?id="+id,Map.class);
		return data;
	}
}
